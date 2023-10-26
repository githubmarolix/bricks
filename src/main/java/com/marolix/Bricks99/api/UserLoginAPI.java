package com.marolix.Bricks99.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marolix.Bricks99.dto.UserLoginDTO;
import com.marolix.Bricks99.exception.Bricks99Exception;
import com.marolix.Bricks99.service.SellerService;

@RestController
@RequestMapping(value = "login-api")
public class UserLoginAPI {

	@Autowired
	private SellerService userLoginService;

	@PostMapping(value = "/login")
	public ResponseEntity<String> validLogin(@RequestBody UserLoginDTO loginDTO) throws Bricks99Exception {
	
		String b = userLoginService.validLogin(loginDTO);
		return new ResponseEntity<>(b, HttpStatus.OK);
	}
}
