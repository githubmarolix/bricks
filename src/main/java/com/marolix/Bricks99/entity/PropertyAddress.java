package com.marolix.Bricks99.entity;

import javax.persistence.*;

@Entity
@Table(name = "property_address")
public class PropertyAddress {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "address_id")
	private Integer addressId;
	private String addressLine;
	@Column(unique = true, nullable = false)
	private String surveyNo;

	

	@Column(name = "pincode", nullable = false)
	private String pincode;
	private String city;
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

	@Column(name = "state", nullable = false)
	private String state;

	public PropertyAddress() {
		super();
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

	public PropertyAddress(Integer addressId, String addressLine, String surveyNo, String pincode, String city,
			String state) {
		super();
		this.addressId = addressId;
		this.addressLine = addressLine;
		this.surveyNo = surveyNo;
		this.pincode = pincode;
		this.city = city;
		this.state = state;
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
