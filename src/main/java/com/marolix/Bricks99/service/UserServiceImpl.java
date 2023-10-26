package com.marolix.Bricks99.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marolix.Bricks99.dto.UserDTO;
import com.marolix.Bricks99.entity.Seller;
import com.marolix.Bricks99.entity.User;
import com.marolix.Bricks99.entity.UserType;
import com.marolix.Bricks99.exception.Bricks99Exception;
import com.marolix.Bricks99.repository.UserRepository;
import com.marolix.Bricks99.repository.UserTypeRepository;
import com.marolix.Bricks99.utility.HashingUtility;

@Service(value = "userService")
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private Environment environment;
	@Autowired
	private UserTypeRepository userTypeRepository;
	@Autowired
	private SellerService sellerService;

	@Override
	public UserDTO addUser(UserDTO dto) throws Bricks99Exception {

		User sr = userRepository.findByContact(dto.getContact());
		if (sr != null)
			throw new Bricks99Exception(environment.getProperty("UserService.Phone_Exists"));
		User sr2 = userRepository.findByEmail(dto.getEmail());
		if (sr2 != null)
			throw new Bricks99Exception(environment.getProperty("UserService.Email_Exists"));
		User user = new User();
		user.setFirstName(dto.getFirstName());
		user.setLastName(dto.getLastName());
		user.setContact(dto.getContact());
		user.setEmail(dto.getEmail());
		user.setPassword(HashingUtility.hashedString(dto.getPassword()));
		UserType ut = userTypeRepository.findByUserTypeName(dto.getUserType().getUserTypeName().toUpperCase());
		if (ut == null)
			throw new Bricks99Exception(environment.getProperty("UserService.usertype_not_found"));
		user.setUserType(ut);
		user.setRegisteredAt(LocalDateTime.now());
		user.setEmail_verified(true);
		user.setContact_verified(false);
		user = userRepository.save(user);
		System.out.println(user);

		sellerService.registerSeller(user);
		UserDTO udto = new UserDTO();
		udto.setUserId(user.getUserId());
		return udto;
	}

	@Override
	public void loginUser(UserDTO dto) throws Bricks99Exception {
		if (dto.getPassword() == null)
			throw new Bricks99Exception(environment.getProperty("USerService.Password_not_found"));
		if (dto.getContact() != null) {
			User user = userRepository.findByContact(dto.getContact());
			if (user == null) {
				throw new Bricks99Exception(environment.getProperty("UserService.contat_not_found"));
			}
			if (!dto.getContact().equals(user.getContact())
					|| !HashingUtility.hashedString(dto.getPassword()).equals(user.getPassword()))
				throw new Bricks99Exception(environment.getProperty("UserSevice.invalid_contact_credentials"));
		} else if (dto.getEmail() != null) {
			User user = userRepository.findByEmail(dto.getEmail());
			if (user == null) {
				throw new Bricks99Exception(environment.getProperty("UserService.email_not_found"));
			}
			if (!dto.getEmail().equals(user.getEmail())
					|| !HashingUtility.hashedString(dto.getPassword()).equals(user.getPassword()))
				throw new Bricks99Exception(environment.getProperty("UserSevice.invalid_email_credentials"));
		}
		else
			throw new Bricks99Exception(environment.getProperty("UserSevice.phone_and_email_not_found"));
	}

}
