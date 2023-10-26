package com.marolix.Bricks99.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.marolix.Bricks99.entity.PropertyDetails;

public interface PropertyRepository extends PagingAndSortingRepository<PropertyDetails, Integer>{
	PropertyDetails findByPropertyName(String name);
	List<PropertyDetails> findByAddressSurveyNo(String surveyNumber);
	public List<PropertyDetails>findByAddressCity(String localityName);
	List<PropertyDetails> findByPropertyPriceLessThanEqual(Double price);
List<PropertyDetails>findBySellerSellerId(Integer sellerId,Pageable pg);
}
