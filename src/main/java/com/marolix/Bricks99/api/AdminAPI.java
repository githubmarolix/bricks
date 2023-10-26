package com.marolix.Bricks99.api;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marolix.Bricks99.dto.SellerDTO;
import com.marolix.Bricks99.dto.StatesDTO;
import com.marolix.Bricks99.dto.UserTypeDTO;
import com.marolix.Bricks99.entity.SellerStatus;
import com.marolix.Bricks99.exception.Bricks99Exception;
import com.marolix.Bricks99.service.AddressUtilityService;
import com.marolix.Bricks99.service.AdminService;

@CrossOrigin
@Validated
@RestController
@RequestMapping(value = "admin-api")
public class AdminAPI {

	@Autowired
	private AdminService adminService;
	@Autowired
	private Environment environment;

	@Autowired
	private AddressUtilityService addressUtilityService;

	@GetMapping(value = "view-all/{status}")
	public ResponseEntity<List<SellerDTO>> viewRegisteredSellers(
			@PathVariable @NotNull(message = "{invalid.status.search}") SellerStatus status) throws Bricks99Exception {
		return new ResponseEntity<List<SellerDTO>>(adminService.viewAllRegisteredSellers(status), HttpStatus.OK);
	}

	@GetMapping(value = "view-all")
	public ResponseEntity<List<SellerDTO>> viewSellers() throws Bricks99Exception {
		return new ResponseEntity<List<SellerDTO>>(adminService.viewAllSellers(), HttpStatus.OK);
	}

	@GetMapping(value = "view-seller/{sellerId}")
	public ResponseEntity<SellerDTO> viewRegisteredSeller(
			@PathVariable @NotNull(message = "{invalid.status.search}") Integer sellerId) throws Bricks99Exception {
		return new ResponseEntity<SellerDTO>(adminService.viewRegisteredSeller(sellerId), HttpStatus.OK);
	}

	@PutMapping(value = "sellers/approve")
	public ResponseEntity<?> approveAllSellers() throws Bricks99Exception {
		adminService.approveAllSellers();

		return new ResponseEntity<>(environment.getProperty("AdminAPI.APPROVED.ALL"), HttpStatus.OK);
	}

	@PutMapping(value = "sellers/approve/{sellerId}")
	public ResponseEntity<?> approveSeller(@PathVariable Integer sellerId) throws Bricks99Exception {
		adminService.approveSeller(sellerId);

		return new ResponseEntity<>(environment.getProperty("AdminAPI.APPROVED"), HttpStatus.OK);
	}

	@PutMapping(value = "sellers/reject")
	public ResponseEntity<?> rejectAllSellers() throws Bricks99Exception {
		adminService.rejectAllSellers();

		return new ResponseEntity<>(environment.getProperty("AdminAPI.REJECTED.ALL"), HttpStatus.OK);
	}

	@PutMapping(value = "sellers/reject/{sellerId}")
	public ResponseEntity<?> rejectSeller(@PathVariable Integer sellerId) throws Bricks99Exception {
		adminService.rejectSeller(sellerId);

		return new ResponseEntity<>(environment.getProperty("AdminAPI.REJECTED"), HttpStatus.OK);
	}

//	@GetMapping(value = "/report")
//	public ResponseEntity<?> generateReport(HttpServletResponse response) throws Bricks99Exception {
//		System.out.println("check");
//		response.setContentType("application/octet-stream");
//		String hKey="Content-Disposition";
//		String hValue="attachment; filename=users.xlsx";
//		response.setHeader(hKey, hValue);
//		adminService.downloadSellers(response);
//
//		return new ResponseEntity<>("generated", HttpStatus.OK);
//	}
	@GetMapping(value = "/report")
	public ResponseEntity<?> generateReport() throws Bricks99Exception {
		adminService.downloadSellers();
		return new ResponseEntity<>("generated", HttpStatus.OK);
	}

	@PostMapping(value = "/addUserType")
	public ResponseEntity<UserTypeDTO> addUSerType(@RequestBody UserTypeDTO dto) throws Bricks99Exception {
		return new ResponseEntity<UserTypeDTO>(adminService.addUSerType(dto), HttpStatus.CREATED);
	}

	@PostMapping(value="/add-state")
	public ResponseEntity<StatesDTO> addState(@RequestBody StatesDTO dto) throws Bricks99Exception{
		
		return new ResponseEntity<StatesDTO>(addressUtilityService.addStates(dto),HttpStatus.CREATED);
	}
}
