package com.marolix.Bricks99.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class SellerAddressDTO {
	@NotNull(message="{address.line1.empty}")
	@Pattern(regexp = "([A-Za-z0-9,-]+)",message="{address.line1.invalid}")
	private String addressLine1;
	@NotNull(message="{address.line2.empty}")
	private String addressLine2;
	@NotNull(message="{address.city.empty}")
	private String city;
	@NotNull(message="{address.state.empty}")
	private String state;
	@NotNull(message="{address.pincode.empty}")
	private String pincode;

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}



	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	@Override
	public String toString() {
		return "SellerAddressDTO [addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2 + ", city=" + city
				+ ", state=" + state + ", pincode=" + pincode + "]";
	}
	
	

}
