package com.marolix.Bricks99.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marolix.Bricks99.dto.StatesDTO;
import com.marolix.Bricks99.entity.States;
import com.marolix.Bricks99.exception.Bricks99Exception;
import com.marolix.Bricks99.repository.StatesRepository;

@Service(value = "adressUtility")
@Transactional
public class AddressUtilityServiceImpl implements AddressUtilityService {
	@Autowired
	private StatesRepository statesRepository;
	@Autowired
	private Environment environment;

	public StatesDTO addStates(StatesDTO dto) throws Bricks99Exception {
		if (dto.getStateName() == null)
			throw new Bricks99Exception(environment.getProperty("AddressService.state.empty"));
		States st = statesRepository.findByStateName(dto.getStateName().toUpperCase());
		if (st != null)
			throw new Bricks99Exception(environment.getProperty("AddressService.state.present"));
		st = new States();
		st.setStateName(dto.getStateName().toUpperCase());
		st=statesRepository.save(st);
		dto.setStateName(st.getStateName());
		dto.setStateId(st.getStateId());
		return dto;
	}

}
