package com.marolix.Bricks99.service;

import java.util.List;
import com.marolix.Bricks99.dto.PropertyDetailsDTO;
import com.marolix.Bricks99.exception.Bricks99Exception;

public interface PropertyService {
	 public PropertyDetailsDTO addProperty(PropertyDetailsDTO propertyDto) throws Bricks99Exception;
	 public List<PropertyDetailsDTO> findAllPropertiesUsingPagination(Integer pageNumber, Integer pageSize)throws Bricks99Exception;
	public List<PropertyDetailsDTO>findByLocalityName(String LocalityName)throws Bricks99Exception;
	public List<PropertyDetailsDTO>findByPropertyPrice(Double price)throws Bricks99Exception;
	public List<PropertyDetailsDTO>findPropertiesBySellerId(Integer SellerId,Integer pgNo,Integer pgSize)throws Bricks99Exception;
	public PropertyDetailsDTO findPropertiesByPropertyId(Integer SellerId)throws Bricks99Exception;
	public List<PropertyDetailsDTO> findAllProperties(Integer... sellerId)throws Bricks99Exception;

}
