package com.marolix.Bricks99.api;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marolix.Bricks99.dto.PropertyDetailsDTO;
import com.marolix.Bricks99.exception.Bricks99Exception;
import com.marolix.Bricks99.service.PropertyService;

@CrossOrigin
@RestController
@Validated
@RequestMapping(value = "property-api")
public class PropertyApi {

	@Autowired
	private PropertyService propertyservice;
@Autowired
private Environment environment;
//	@PostMapping(value = "/add-property")
//	public ResponseEntity<PropertyDetailsDTO> addDetails(@RequestBody PropertyDetailsDTO addressDTO)
//			throws Bricks99Exception {
//		PropertyDetailsDTO addedPropertyDTO = propertyservice.addProperty(addressDTO);
//		return new ResponseEntity<>(addedPropertyDTO, HttpStatus.CREATED);
//	}

	@PostMapping(value = "/add-property")
	public ResponseEntity<PropertyDetailsDTO> addDetails(@RequestParam(value="files")  MultipartFile[] files ,@RequestParam(value="data")@Valid String  propDTO)
			throws Bricks99Exception, IOException {
		ObjectMapper o= new ObjectMapper();
	PropertyDetailsDTO d=	o.readValue(propDTO, PropertyDetailsDTO.class);
	d.setNoOfPhotosUploaded(files.length);
	PropertyDetailsDTO dto=propertyservice.addProperty( d);
	System.out.println(dto.getPropertyId());
	Integer propertyId=dto.getPropertyId();
	int propertyPhotoCount=0;
		for(int i=0;i<files.length;i++) {
			String s=environment.getProperty("property_photos_path");
			s=s+"/propertyId-"+propertyId+"photo-"+i+".jpg";
			System.out.println(s);
			FileOutputStream fos= new FileOutputStream(new File(s));
			
			fos.write(files[i].getBytes());
		
		}
		
	//	propertyservice.addProperty(addressDTO);
	
		return new ResponseEntity<>(dto, HttpStatus.CREATED);
	}
	@GetMapping(value = "/view-all-properties/pgNo/{pageNumber}/pgSize/{pageSize}")
	public ResponseEntity<List<PropertyDetailsDTO>> findProperties(@PathVariable Integer pageNumber,
			@PathVariable Integer pageSize) throws Bricks99Exception {
		List<PropertyDetailsDTO> PropertyDTOList = propertyservice.findAllPropertiesUsingPagination(pageNumber,
				pageSize);
		return new ResponseEntity<>(PropertyDTOList, HttpStatus.OK);
	}

	@GetMapping(value = "/view-by-locality")
	public ResponseEntity<List<PropertyDetailsDTO>> findPropertiesByLocality(@RequestParam String locality)
			throws Bricks99Exception {
		List<PropertyDetailsDTO> PropertyLocality = propertyservice.findByLocalityName(locality);

		return new ResponseEntity<>(PropertyLocality, HttpStatus.OK);
	}

	@GetMapping(value = "/view-by-price")
	public ResponseEntity<List<PropertyDetailsDTO>> findPropertiesByPrice(@RequestParam Double price)
			throws Bricks99Exception {
		List<PropertyDetailsDTO> Propertyprice = propertyservice.findByPropertyPrice(price);

		return new ResponseEntity<>(Propertyprice, HttpStatus.OK);
	}

	@GetMapping(value = "/view-all/{sellerId}/pgNo/{pgNo}/pgSize/{pgSize}")
	public ResponseEntity<List<PropertyDetailsDTO>> findPropertiesBySellerId(@PathVariable Integer sellerId,
			@PathVariable Integer pgNo, @PathVariable Integer pgSize) throws Bricks99Exception {
		List<PropertyDetailsDTO> Propertyprice = propertyservice.findPropertiesBySellerId(sellerId, pgNo, pgSize);

		return new ResponseEntity<>(Propertyprice, HttpStatus.OK);
	}

	@GetMapping(value = "/view/{propertyId}")
	public ResponseEntity<PropertyDetailsDTO> findPropertyByPropertyId(@PathVariable Integer propertyId)
			throws Bricks99Exception {
		PropertyDetailsDTO Propertyprice = propertyservice.findPropertiesByPropertyId(propertyId);

		return new ResponseEntity<>(Propertyprice, HttpStatus.OK);
	}

	@GetMapping(value = "/view-all")
	public ResponseEntity<List<PropertyDetailsDTO>> findAllProperties() throws Bricks99Exception {
		System.out.println("api");
		List<PropertyDetailsDTO> PropertyDTOList = propertyservice.findAllProperties();
		return new ResponseEntity<>(PropertyDTOList, HttpStatus.OK);
	}

	@GetMapping(value = "/view-all/{sellerId}")
	public ResponseEntity<List<PropertyDetailsDTO>> findAllPropertiesBySellerId(@PathVariable Integer sellerId)
			throws Bricks99Exception {
		List<PropertyDetailsDTO> PropertyDTOList = propertyservice.findAllProperties(sellerId);
		return new ResponseEntity<>(PropertyDTOList, HttpStatus.OK);
	}
}
