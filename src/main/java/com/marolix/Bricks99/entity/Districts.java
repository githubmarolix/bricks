package com.marolix.Bricks99.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Districts {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer districtId;
	private String districtName;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "state_id")
	private States states;
	
	
	
	public Integer getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public States getStates() {
		return states;
	}
	public void setStates(States states) {
		this.states = states;
	}
	
	
}
