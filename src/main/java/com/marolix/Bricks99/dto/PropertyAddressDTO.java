package com.marolix.Bricks99.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class PropertyAddressDTO {

	private Integer addressId;
	private String addressLine;
	public String getAddressLine() {
		return addressLine;
	}

	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@NotNull(message="{property.address.surveyNo.empty}")
	@Pattern(regexp = "^[A-Za-z0-9/.,-]+$",message="{property.address.surveyNo.invalid}")
	private String surveyNo;
	@NotNull(message="{property.address.locality.empty}")
	@Pattern(regexp = "^[A-Za-z0-9\\s,-]+$",message="{property.address.locality.invalid}")
	private String city;

	@NotNull(message="{property.address.pincode.empty}")
	@Pattern(regexp = "^\\d{6}$",message="{property.address.pincode.invalid}") 
	private String pincode;
	@NotNull(message="{property.address.state.empty}")
	@Pattern(regexp =  "^[A-Za-z\\s]+$",message="{property.address.state.invalid}")
	private String state;

	public PropertyAddressDTO() {
		super();
	}

	public PropertyAddressDTO(Integer addressId, String surveyNo, String locality, String pincode,
			String state) {
		super();
		this.addressId = addressId;
		this.surveyNo = surveyNo;
		this.city = locality;
//		this.areaInSqft = areaInSqft;
		this.pincode = pincode;
		this.state = state;
	}

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public String getSurveyNo() {
		return surveyNo;
	}

	public void setSurveyNo(String surveyNo) {
		this.surveyNo = surveyNo;
	}

	



	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}



	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
