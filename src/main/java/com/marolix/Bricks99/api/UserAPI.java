package com.marolix.Bricks99.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marolix.Bricks99.dto.UserDTO;
import com.marolix.Bricks99.exception.Bricks99Exception;
import com.marolix.Bricks99.service.UserService;

@RequestMapping(value = "user-api")
@RestController
@CrossOrigin
public class UserAPI {
	@Autowired
	private UserService userService;
	@Autowired
	private Environment environment;

	@PostMapping(value = "/register")
	public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO dto) throws Bricks99Exception {
		return new ResponseEntity<UserDTO>(userService.addUser(dto), HttpStatus.CREATED);
	}

	@GetMapping(value = "/login")
	public ResponseEntity<String> loginUser(@RequestBody UserDTO dto) throws Bricks99Exception {
		userService.loginUser(dto);
		return new ResponseEntity<String>(environment.getProperty("LOGIN_SUCCESS"), HttpStatus.OK);
	}

}
