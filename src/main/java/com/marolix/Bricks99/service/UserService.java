package com.marolix.Bricks99.service;

import com.marolix.Bricks99.dto.UserDTO;
import com.marolix.Bricks99.exception.Bricks99Exception;

public interface UserService {
	public UserDTO addUser(UserDTO dto) throws Bricks99Exception;

	public void loginUser(UserDTO dto) throws Bricks99Exception;
}
