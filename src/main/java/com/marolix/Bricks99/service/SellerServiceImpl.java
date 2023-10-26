package com.marolix.Bricks99.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marolix.Bricks99.dto.SellerAddressDTO;
import com.marolix.Bricks99.dto.SellerDTO;
import com.marolix.Bricks99.dto.UpdateDetailsDTO;
import com.marolix.Bricks99.dto.UserLoginDTO;
import com.marolix.Bricks99.entity.SellerAddress;
import com.marolix.Bricks99.entity.Seller;
import com.marolix.Bricks99.entity.SellerStatus;
import com.marolix.Bricks99.entity.User;
import com.marolix.Bricks99.entity.UserLogin;
import com.marolix.Bricks99.exception.Bricks99Exception;
import com.marolix.Bricks99.repository.SellerRepository;
import com.marolix.Bricks99.repository.UserLoginRepository;
import com.marolix.Bricks99.utility.HashingUtility;

@Service(value = "sellerService")
@Transactional
public class SellerServiceImpl implements SellerService {

	@Autowired
	public UserLoginRepository userLoginRepository;
	@Autowired
	private SellerRepository sellerRepository;
	@Autowired
	private Environment environment;

	private String validation(String variable, String password, UserLogin ulogin) throws Bricks99Exception {
		System.out.println(variable);
		if (ulogin == null)
			throw new Bricks99Exception(environment.getProperty("UserService.USER_NOT_FOUND"));

		if (variable.equals(ulogin.getContact())
				&& HashingUtility.hashedString(password).equals(ulogin.getPassword())) {
			return "UserService.Login_STATUS";
		} else
			throw new Bricks99Exception(environment.getProperty("UserService.Invalid_Login"));
	}

	@Override
	public String validLogin(UserLoginDTO dto) throws Bricks99Exception {
		UserLogin ulogin = null;
		System.out.println(dto);
		if (dto.getUser_name() != null) {
			ulogin = userLoginRepository.findByUserName(dto.getUser_name());
			return validation(dto.getUser_name(), dto.getPassword(), ulogin);
		} else if (dto.getContact() != null) {
			ulogin = userLoginRepository.findByContact(dto.getContact());
			return validation(dto.getContact(), dto.getPassword(), ulogin);

		} else if (dto.getEmail() != null)
			ulogin = userLoginRepository.findByEmail(dto.getEmail());
		return validation(dto.getEmail(), dto.getPassword(), ulogin);

	}

	@Override
	public SellerDTO sellerRegistration(SellerDTO sellerDTO) throws Bricks99Exception {

		Seller sr = sellerRepository.findByContact(sellerDTO.getContact());
		if (sr != null)
			throw new Bricks99Exception(environment.getProperty("SellerService.Phone_Exists"));
		Seller sr2 = sellerRepository.findByEmail(sellerDTO.getEmail());
		if (sr2 != null)
			throw new Bricks99Exception(environment.getProperty("SellerService.Email_Exists"));
//		Seller sr3 = sellerRepository.findByAdhaar(sellerDTO.getAdhaar());
//		if (sr3 != null)
//			throw new Bricks99Exception(environment.getProperty("SellerService.Adhaar_Exists"));
		Seller newSR = SellerDTO.dtoToEntity(sellerDTO);

		Integer i = sellerRepository.save(newSR).getSellerId();
		sellerDTO.setSellerId(i);
		UserLogin u = new UserLogin();
		u.setPassword(sellerDTO.getPassword());
		String s[] = sellerDTO.getEmail().split("@");
		String username = s[0] + sellerDTO.getContact().substring(2, 6);
		u.setUserName(username);

		u.setContact(sellerDTO.getContact());
		u.setEmail(sellerDTO.getEmail());
		userLoginRepository.save(u);
		return sellerDTO;
	}

	@Override
	public SellerDTO sellerValidLogin(UserLoginDTO dto) throws Bricks99Exception {

		Seller s = sellerRepository.findByContact(dto.getContact());

		if (s == null)
			throw new Bricks99Exception(environment.getProperty("SellerService.Seller_NOT_REGISTERED"));
		if (!dto.getContact().equals(s.getContact())
				|| !HashingUtility.hashedString(dto.getPassword()).equals(s.getPassword())) {

			throw new Bricks99Exception(environment.getProperty("SellerService.INVALID_CREDENTIALS"));
		}
		return SellerDTO.entityToDTO(s);
	}

	@Override
	public SellerDTO updateDetails(UpdateDetailsDTO dto, Integer sellerId) throws Bricks99Exception {

		Optional<Seller> opt = sellerRepository.findById(sellerId);
		Seller sr = opt.orElseThrow(() -> new Bricks99Exception("SellerService.Seller_NOT_FOUND"));

		Seller sr1 = sellerRepository.findByContact(dto.getContact());

		Seller sr2 = sellerRepository.findByEmail(dto.getEmail());

		if (sr2 == null) {
			sr.setEmail(dto.getEmail());
		} else {
			if (!sr2.equals(sr)) {

				throw new Bricks99Exception(environment.getProperty("SellerService.Email_Exists"));
			} else
				sr.setEmail(dto.getEmail());

		}
		if (sr1 == null) {
			sr.setContact(dto.getContact());
		} else {
			if (!sr1.equals(sr)) {

				throw new Bricks99Exception(environment.getProperty("SellerService.Phone_Exists"));
			} else
				sr.setContact(dto.getContact());

		}
		sr.setFirstName(dto.getFirstName());
		sr.setLastName(dto.getLastName());

		sellerRepository.save(sr);
		System.out.println("check6");
		return SellerDTO.entityToDTO(sr);
	}

	@Override
	@Transactional
	public SellerDTO registerSeller(User user) throws Bricks99Exception {
		Seller sr = sellerRepository.findByContact(user.getContact());
		if (sr != null)
			throw new Bricks99Exception(environment.getProperty("SellerService.Phone_Exists"));
		Seller sr2 = sellerRepository.findByEmail(user.getEmail());
		if (sr2 != null)
			throw new Bricks99Exception(environment.getProperty("SellerService.Email_Exists"));
		Seller seller = new Seller();
		seller.setContact(user.getContact());
		seller.setEmail(user.getEmail());
		seller.setPassword(user.getPassword());
		seller.setUser(user);
		seller.setFirstName(user.getFirstName());
		seller.setLastName(user.getLastName());
		seller.setStatus(SellerStatus.PENDING);
		seller = sellerRepository.save(seller);

		SellerDTO dto = new SellerDTO();
		dto.setSellerId(seller.getSellerId());
		return dto;
	}

}
