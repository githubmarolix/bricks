package com.marolix.Bricks99.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.marolix.Bricks99.dto.SellerDTO;
import com.marolix.Bricks99.dto.UserTypeDTO;
import com.marolix.Bricks99.entity.SellerStatus;
import com.marolix.Bricks99.exception.Bricks99Exception;

public interface AdminService {
	public List<SellerDTO> viewAllRegisteredSellers(SellerStatus status) throws Bricks99Exception;

	public SellerDTO viewRegisteredSeller(Integer seller_id) throws Bricks99Exception;

	public void approveAllSellers() throws Bricks99Exception;

	public void approveSeller(Integer seller_id) throws Bricks99Exception;

	public void rejectSeller(Integer seller_id) throws Bricks99Exception;

	public void rejectAllSellers() throws Bricks99Exception;

	public List<SellerDTO> viewAllSellers() throws Bricks99Exception;

	public void downloadSellers() throws Bricks99Exception;

	public UserTypeDTO addUSerType(UserTypeDTO usertypeDTO) throws Bricks99Exception;
}
