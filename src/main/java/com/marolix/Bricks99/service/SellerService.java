package com.marolix.Bricks99.service;

import com.marolix.Bricks99.dto.SellerDTO;
import com.marolix.Bricks99.dto.UpdateDetailsDTO;
import com.marolix.Bricks99.dto.UserLoginDTO;
import com.marolix.Bricks99.entity.User;
import com.marolix.Bricks99.exception.Bricks99Exception;

public interface SellerService {
	public String validLogin(UserLoginDTO dto) throws Bricks99Exception;

	public SellerDTO sellerRegistration(SellerDTO sellerDTO) throws Bricks99Exception;

	SellerDTO sellerValidLogin(UserLoginDTO dto) throws Bricks99Exception;

	SellerDTO updateDetails(UpdateDetailsDTO dto, Integer sellerId) throws Bricks99Exception;

	public SellerDTO registerSeller(User user) throws Bricks99Exception;
}
