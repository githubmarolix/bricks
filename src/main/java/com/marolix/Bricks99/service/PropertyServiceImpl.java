package com.marolix.Bricks99.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marolix.Bricks99.dto.PropertyAddressDTO;
import com.marolix.Bricks99.dto.PropertyDetailsDTO;
import com.marolix.Bricks99.entity.PropertyAddress;
import com.marolix.Bricks99.entity.PropertyDetails;
import com.marolix.Bricks99.entity.Seller;
import com.marolix.Bricks99.exception.Bricks99Exception;
import com.marolix.Bricks99.repository.AddressRepository;
import com.marolix.Bricks99.repository.PropertyRepository;
import com.marolix.Bricks99.repository.SellerRepository;

@Service(value = "propertyService")
@Transactional
public class PropertyServiceImpl implements PropertyService {

	@Autowired
	private SellerRepository sellerRepo;
	@Autowired
	private PropertyRepository propertyRepo;
	@Autowired
	private AddressRepository addressRepo;
	@Autowired
	private Environment environment;
	@Autowired
	private EmailSender emailSender;

	@Override
	public PropertyDetailsDTO addProperty(PropertyDetailsDTO propertyDto) throws Bricks99Exception {

		Optional<Seller> s1 = sellerRepo.findById(propertyDto.getSellerId());
		Seller seller = s1
				.orElseThrow(() -> new Bricks99Exception(environment.getProperty("PropertyService.SELLER_NOT_FOUND")));
		if (seller.getStatus().toString().equalsIgnoreCase("Pending"))
			throw new Bricks99Exception(environment.getProperty("PropertyService.SellerNotVerified"));

		List<PropertyDetails> propertyEntity = propertyRepo
				.findByAddressSurveyNo(propertyDto.getPropertyAddress().getSurveyNo());

		if (!propertyEntity.isEmpty()) {
			throw new Bricks99Exception(environment.getProperty("PropertyService.SurveynoAlreadyExists"));
		}
		Integer i = propertyRepo.save(PropertyDetailsDTO.dtoToEntity(propertyDto)).getPropertyId();
		propertyDto.setPropertyId(i);
//		String to = "reddysbharathofficial@gmail.com";
//		String subject = "Test Email";
//		String body = "Hello, this is a test email from Spring Boot.";
//System.out.println("mail body set");
//		emailSender.sendEmail(to, subject, body);
//		System.out.println("after send mail");

		return propertyDto;
	}

	@Override
	public List<PropertyDetailsDTO> findAllPropertiesUsingPagination(Integer pageNumber, Integer pageSize)
			throws Bricks99Exception {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);

		Page<PropertyDetails> propertyPage = propertyRepo.findAll(pageable);
		if (propertyPage.hasContent()) {
			List<PropertyDetails> propertyList = propertyPage.getContent();
			return propertyList.stream().map(property -> PropertyDetailsDTO.entityToDTO(property)

			).collect(Collectors.toList());
		}
		throw new Bricks99Exception(environment.getProperty("PropertyService.PROPERTY_NOT_FOUND"));
	}

	@Override
	public List<PropertyDetailsDTO> findByLocalityName(String localityName) throws Bricks99Exception {
		List<PropertyDetails> propertyList = propertyRepo.findByAddressCity(localityName);

		if (propertyList.isEmpty()) {
			throw new Bricks99Exception(environment.getProperty("PropertyService.LOCALITY_NOT_FOUND"));
		}

		List<PropertyDetailsDTO> propertyDTOList = propertyList.stream()
				.map(property -> PropertyDetailsDTO.entityToDTO(property)).collect(Collectors.toList());

		return propertyDTOList;
	}

	@Override
	public List<PropertyDetailsDTO> findByPropertyPrice(Double price) throws Bricks99Exception {
		List<PropertyDetails> propertyList = propertyRepo.findByPropertyPriceLessThanEqual(price);
		if (propertyList.isEmpty()) {
			throw new Bricks99Exception(environment.getProperty("PropertyService.PRICE_NOT_FOUND"));
		}
		List<PropertyDetailsDTO> propertyDTOList = propertyList.stream()
				.map(property -> PropertyDetailsDTO.entityToDTO(property)).collect(Collectors.toList());

		return propertyDTOList;
	}

	@Override
	public List<PropertyDetailsDTO> findPropertiesBySellerId(Integer sellerId, Integer pgNo, Integer pgSize)
			throws Bricks99Exception {
		Optional<Seller> o = sellerRepo.findById(sellerId);
		Seller sr = o
				.orElseThrow(() -> new Bricks99Exception(environment.getProperty("PropertyService.SELLER_NOT_FOUND")));
		Pageable pg = PageRequest.of(pgNo, pgSize);
		List<PropertyDetails> propertyDetails = propertyRepo.findBySellerSellerId(sellerId, pg);
		if (propertyDetails.isEmpty()) {
			throw new Bricks99Exception(environment.getProperty("PropertyService.PROPERTY_NOT_FOUND"));
		}

		return propertyDetails.stream().map(property -> PropertyDetailsDTO.entityToDTO(property))
				.collect(Collectors.toList());
	}

	@Override
	public PropertyDetailsDTO findPropertiesByPropertyId(Integer propertyId) throws Bricks99Exception {
		Optional<PropertyDetails> p1 = propertyRepo.findById(propertyId);
		PropertyDetails property = p1
				.orElseThrow(() -> new Bricks99Exception(environment.getProperty("PropertyService.Property_Empty")));

		return PropertyDetailsDTO.entityToDTO(property);
	}

	@Override
	public List<PropertyDetailsDTO> findAllProperties(Integer... id) throws Bricks99Exception {
		System.out.println(id);
		final List<PropertyDetailsDTO> dtos = new ArrayList<PropertyDetailsDTO>();

		Iterable<PropertyDetails> properties = propertyRepo.findAll();

		properties.forEach(t -> {
			dtos.add(PropertyDetailsDTO.entityToDTO(t));

		});
		if (id.length > 0 && id[0] != null) {
			List<PropertyDetailsDTO> dtos2 = dtos.stream().filter(t -> t.getSellerId() == id[0])
					.collect(Collectors.toList());
			return dtos2;
		} else {
			if (dtos.isEmpty()) {
				throw new Bricks99Exception(environment.getProperty("PropertyService.PROPERTY_NOT_FOUND"));
			}
			return dtos;
		}
	}

}
