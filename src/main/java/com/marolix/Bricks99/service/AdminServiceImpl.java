package com.marolix.Bricks99.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.marolix.Bricks99.dto.SellerDTO;
import com.marolix.Bricks99.dto.UserTypeDTO;
import com.marolix.Bricks99.entity.Seller;
import com.marolix.Bricks99.entity.SellerStatus;
import com.marolix.Bricks99.entity.UserType;
import com.marolix.Bricks99.exception.Bricks99Exception;
import com.marolix.Bricks99.repository.SellerRepository;
import com.marolix.Bricks99.repository.UserTypeRepository;

@Service(value = "adminService")
public class AdminServiceImpl implements AdminService {
	@Autowired
	private SellerRepository sellerRepository;
	@Autowired
	private Environment environment;

	@Autowired
	private UserTypeRepository userTypeRepository;

	@Override
	public List<SellerDTO> viewAllRegisteredSellers(SellerStatus status) throws Bricks99Exception {
		List<Seller> list = sellerRepository.findByStatus(status);
		return list.stream().map(p -> {
			SellerDTO dto = SellerDTO.entityToDTO(p);
			return dto;
		}).collect(Collectors.toList());

	}

	@Override
	public SellerDTO viewRegisteredSeller(Integer seller_id) throws Bricks99Exception {
		Optional<Seller> op = sellerRepository.findById(seller_id);
		Seller sr = op
				.orElseThrow(() -> new Bricks99Exception(environment.getProperty("AdminService.SellerNotRegistered")));
		return SellerDTO.entityToDTO(sr);

	}

	@Override
	public void approveAllSellers() throws Bricks99Exception {
		List<Seller> list = sellerRepository.findByStatus(SellerStatus.PENDING);
		if (list == null)
			throw new Bricks99Exception(environment.getProperty("AdminService.NO_NEW_SELLERS_FOUND"));
		else
			list.stream().forEach(t -> {
				t.setStatus(SellerStatus.APPROVED);
				sellerRepository.save(t);
			});

	}

	@Override
	public void approveSeller(Integer seller_id) throws Bricks99Exception {
		Optional<Seller> op = sellerRepository.findById(seller_id);
		Seller sr = op
				.orElseThrow(() -> new Bricks99Exception(environment.getProperty("AdminService.SellerNotRegistered")));
		sr.setStatus(SellerStatus.APPROVED);
		sellerRepository.save(sr);
	}

	@Override
	public void rejectAllSellers() throws Bricks99Exception {
		List<Seller> list = sellerRepository.findByStatus(SellerStatus.PENDING);
		if (list == null)
			throw new Bricks99Exception(environment.getProperty("AdminService.NO_NEW_SELLERS_FOUND"));
		else
			list.stream().forEach(t -> {
				t.setStatus(SellerStatus.REJECTED);
				sellerRepository.save(t);
			});

	}

	@Override
	public void rejectSeller(Integer seller_id) throws Bricks99Exception {
		Optional<Seller> op = sellerRepository.findById(seller_id);
		Seller sr = op
				.orElseThrow(() -> new Bricks99Exception(environment.getProperty("AdminService.SellerNotRegistered")));
		sr.setStatus(SellerStatus.REJECTED);
		sellerRepository.save(sr);
	}

	@Override
	public List<SellerDTO> viewAllSellers() throws Bricks99Exception {
		Iterable<Seller> i = sellerRepository.findAll();
		List<SellerDTO> dto = new ArrayList<SellerDTO>();

		i.forEach(t -> {

			dto.add(SellerDTO.entityToDTO(t));
		});
		if (!dto.isEmpty())
			return dto;
		else
			throw new Bricks99Exception(environment.getProperty("AdminService.NO_SELLERS_FOUND"));

	}

	@Override
	// public void downloadSellers(HttpServletResponse response) throws
	// Bricks99Exception {
	public void downloadSellers() throws Bricks99Exception {
		System.out.println("invoked report generate method");
		List<SellerDTO> list = viewAllSellers();
		// System.out.println(list);
		String downloadsPath = System.getProperty("user.home") + File.separator + "Downloads";
		String outputFilePath = downloadsPath + File.separator + "sellerdata.xlsx";

		// System.out.println(outputFilePath);

		File f = new File(outputFilePath);

		// System.out.println("check before");
		Workbook workbook = new XSSFWorkbook();
		// System.out.println("check before");
		Sheet sheet = workbook.createSheet("SellerData");
		Row headr = sheet.createRow(0);
		Cell hcell = headr.createCell(0);
		hcell.setCellValue("SELLER_ID");
		hcell = headr.createCell(1);
		hcell.setCellValue("FIRST_NAME");
		hcell = headr.createCell(2);
		hcell.setCellValue("LAST_NAME");
		hcell = headr.createCell(3);
		hcell.setCellValue("EMAIL");
		hcell = headr.createCell(4);
		hcell.setCellValue("PHONE_NUMBER");
		hcell = headr.createCell(5);
		hcell.setCellValue("CITY");
		int rowNo = 1;

		for (SellerDTO dto : list) {

			Row row = sheet.createRow(rowNo);

			Cell cell = row.createCell(0);

			cell.setCellValue(dto.getSellerId());
			cell = row.createCell(1);

			cell.setCellValue(dto.getFirstName());
			cell = row.createCell(2);

			cell.setCellValue(dto.getLastName());
			cell = row.createCell(3);

			cell.setCellValue(dto.getEmail());
			cell = row.createCell(4);

			cell.setCellValue(dto.getContact());
			cell = row.createCell(5);

			cell.setCellValue(dto.getAddress().getCity());

			++rowNo;
		}

		try {

			FileOutputStream fos = new FileOutputStream(f);
			// ServletOutputStream fos = response.getOutputStream();
			workbook.write(fos);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			try {
				workbook.close();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

	}

	@Override
	public UserTypeDTO addUSerType(UserTypeDTO usertypeDTO) throws Bricks99Exception {
		UserType ut = userTypeRepository.findByUserTypeName(usertypeDTO.getUserTypeName().toUpperCase());

		if (ut != null)
			throw new Bricks99Exception(environment.getProperty("AdminService.USer_type_exists"));
		ut = new UserType();
		ut.setUserTypeName(usertypeDTO.getUserTypeName().toUpperCase());
		ut = userTypeRepository.save(ut);
		usertypeDTO.setUserTypeId(ut.getUserTypeId());
		usertypeDTO.setUserTypeName(ut.getUserTypeName());
		return usertypeDTO;
	}
}
