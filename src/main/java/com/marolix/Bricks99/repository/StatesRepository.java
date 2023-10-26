package com.marolix.Bricks99.repository;

import org.springframework.data.repository.CrudRepository;

import com.marolix.Bricks99.entity.States;

public interface StatesRepository extends CrudRepository<States, Integer> {
	States findByStateName(String name);
}
