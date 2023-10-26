package com.marolix.Bricks99.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Pincodes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pincodeId;
	private String pincode;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "district_id")
	private Districts district;
	public Integer getPincodeId() {
		return pincodeId;
	}
	public void setPincodeId(Integer pincodeId) {
		this.pincodeId = pincodeId;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public Districts getDistrict() {
		return district;
	}
	public void setDistrict(Districts district) {
		this.district = district;
	}
	
	
	
	
	
}
