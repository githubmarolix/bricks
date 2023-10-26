package com.marolix.Bricks99.service;

import com.marolix.Bricks99.dto.StatesDTO;
import com.marolix.Bricks99.entity.States;
import com.marolix.Bricks99.exception.Bricks99Exception;

public interface AddressUtilityService {

	public StatesDTO addStates(StatesDTO dto) throws Bricks99Exception;
}
