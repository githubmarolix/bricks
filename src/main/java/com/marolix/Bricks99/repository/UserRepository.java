package com.marolix.Bricks99.repository;

import org.springframework.data.repository.CrudRepository;

import com.marolix.Bricks99.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	User findByEmail(String email);

	User findByContact(String contact);

}
