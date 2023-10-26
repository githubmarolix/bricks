package com.marolix.Bricks99.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.marolix.Bricks99.entity.UserLogin;

public interface UserLoginRepository extends CrudRepository<UserLogin, Integer> {
	// select * from user_login where user_name="";QueryApproach
	// @Query(select * from user_login where user_name="";)
	// findDetilsBasedOnUserName();
	UserLogin findByUserName(String userName);

	List<UserLogin> findByUserRole(String userRole);

	UserLogin findByEmail(String email);

	UserLogin findByContact(String contact);
}
