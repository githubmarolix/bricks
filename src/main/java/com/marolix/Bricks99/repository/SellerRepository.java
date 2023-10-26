package com.marolix.Bricks99.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.marolix.Bricks99.entity.Seller;
import com.marolix.Bricks99.entity.SellerStatus;

public interface SellerRepository extends CrudRepository<Seller, Integer> {
	Seller findByEmail(String email);

	Seller findByContact(String contactNumber);

	List<Seller> findByStatus(SellerStatus status);
	
	
}
