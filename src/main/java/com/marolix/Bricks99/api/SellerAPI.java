package com.marolix.Bricks99.api;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.marolix.Bricks99.dto.SellerDTO;
import com.marolix.Bricks99.dto.UpdateDetailsDTO;
import com.marolix.Bricks99.dto.UserLoginDTO;
import com.marolix.Bricks99.exception.Bricks99Exception;
import com.marolix.Bricks99.service.SellerService;

@CrossOrigin
@RestController
@RequestMapping(value = "seller-api")
public class SellerAPI {
	static int i = 0;
	static int j = 0;
	@Autowired
	private SellerService sellerService;
	@Autowired
	private Environment environment;

	@PostMapping(value = "register")
	public ResponseEntity<SellerDTO> registerSeller(@RequestBody @Valid SellerDTO sellerDTO)
			throws Bricks99Exception {

		return new ResponseEntity<SellerDTO>(sellerService.sellerRegistration(sellerDTO),
				HttpStatus.CREATED);
	}

//	@PostMapping(value = "register")
//	public ResponseEntity<SellerRegistrationDTO> registerSeller(@RequestParam(value = "file") MultipartFile[] files,
//			@RequestBody @Valid SellerRegistrationDTO sellerDTO) throws Bricks99Exception, IOException {
//
//		SellerRegistrationDTO sr = sellerService.sellerRegistration(sellerDTO);
//		Integer sellerId = sr.getSellerId();
//		if (files.length > 0) {
//			for (int i = 0; i < files.length; i++) {
//				MultipartFile f = files[i];
//				byte[] b = f.getBytes();
//				System.out.println(f.getOriginalFilename());
//			}
//		}
//
//		return new ResponseEntity<SellerRegistrationDTO>(sr, HttpStatus.CREATED);
//
//	}

	@PostMapping(value = "login")
	public ResponseEntity<SellerDTO> validLogin(@RequestBody UserLoginDTO dto) throws Bricks99Exception {

		return new ResponseEntity<SellerDTO>(sellerService.sellerValidLogin(dto), HttpStatus.OK);
	}

	@PutMapping(value = "/update/{sellerId}")
	public ResponseEntity<SellerDTO> updatePersonalDetails(@RequestBody UpdateDetailsDTO dto,
			@PathVariable Integer sellerId) throws Bricks99Exception {

		return new ResponseEntity<SellerDTO>(sellerService.updateDetails(dto, sellerId), HttpStatus.OK);
	}

	@PostMapping(value = "/upload")
	public ResponseEntity<String> uploadAdhaar(@RequestBody MultipartFile file) throws IOException {
		System.out.println("invoked upload ");
		byte[] b = file.getBytes();
		File f = new File(environment.getProperty("seller_adhaar_pic_location") + ++i + ".jpg");

		File f2 = new File(environment.getProperty("adhaarupload")

				+ i + ".jpg");
		System.out.print(i + ".jpg");
		FileOutputStream fos = new FileOutputStream(f);
		FileOutputStream fos2 = new FileOutputStream(f2);
		fos.write(b);
		fos2.write(b);
		fos.close();
		fos2.close();
		return new ResponseEntity<String>("file uploaded successfully", HttpStatus.CREATED);
	}

	@PostMapping(value = "/upload-profile")
	public ResponseEntity<String> uploadProfile(@RequestBody MultipartFile file) throws IOException {

		byte[] b = file.getBytes();
		File f = new File(environment.getProperty("seller_page_profile_pic_location") + ++j + ".jpg");
		File f2 = new File(environment.getProperty("admin_page_profile_pic_location") + j + ".jpg");
		FileOutputStream fos = new FileOutputStream(f);
		FileOutputStream fos2 = new FileOutputStream(f2);
		fos.write(b);
		fos2.write(b);
		fos.close();
		fos2.close();
		return new ResponseEntity<String>("profile uploaded successfully", HttpStatus.CREATED);
	}

}
