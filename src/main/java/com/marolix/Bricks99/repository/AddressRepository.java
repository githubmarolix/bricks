package com.marolix.Bricks99.repository;



import org.springframework.data.repository.CrudRepository;

import com.marolix.Bricks99.entity.PropertyAddress;

public interface AddressRepository extends CrudRepository<PropertyAddress, Integer>{
	
	
}
