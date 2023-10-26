package com.marolix.Bricks99.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PropertyDetails")
public class PropertyDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "property_id")
	private Integer propertyId;

	@Column(name = " property_name")
	private String propertyName;

	@Column(name = "property_type")
	private String propertyType;

	@Column(name = "property_price")
	private double propertyPrice;

	@Column(name = "number_of_rooms")
	private String numberOfRooms;
	@Column(name = "area_in_sqft", nullable = false)
	private Double areaInSqft;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	private PropertyAddress address;
	@Column(nullable = false)
	private Integer bathRooms;
	@Column(nullable = false)
	private Integer bedRooms;
	@Column(nullable = false)
	private String description;
	@Column(nullable = false)
	private String category;
	@Column(nullable = false)
	private Integer buildingAge;
	@Column(nullable = false)
	private Integer noOfPhotos;

	public Integer getNoOfPhotos() {
		return noOfPhotos;
	}

	public void setNoOfPhotos(Integer noOfPhotos) {
		this.noOfPhotos = noOfPhotos;
	}

	public Integer getBuildingAge() {
		return buildingAge;
	}

	public void setBuildingAge(Integer buildingAge) {
		this.buildingAge = buildingAge;
	}

	public Integer getBathRooms() {
		return bathRooms;
	}

	public void setBathRooms(Integer bathRooms) {
		this.bathRooms = bathRooms;
	}

	public Integer getBedRooms() {
		return bedRooms;
	}

	public void setBedRooms(Integer bedRooms) {
		this.bedRooms = bedRooms;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setAreaInSqft(Double areaInSqft) {
		this.areaInSqft = areaInSqft;
	}

	// @ManyToOne(cascade = CascadeType.ALL)
	@ManyToOne
	@JoinColumn(name = "registration_id")
	private Seller seller;

	public PropertyDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PropertyDetails(String propertyName, String propertyType, double propertyPrice, String numberOfRooms,
			PropertyAddress addressEntity) {
		this.propertyName = propertyName;
		this.propertyType = propertyType;
		this.propertyPrice = propertyPrice;
		this.numberOfRooms = numberOfRooms;
		this.address = addressEntity;
	}

	public double getAreaInSqft() {
		return areaInSqft;
	}

	public void setAreaInSqft(double areaInSqft) {
		this.areaInSqft = areaInSqft;
	}

	public Integer getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(Integer propertyId) {
		this.propertyId = propertyId;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	public double getPropertyPrice() {
		return propertyPrice;
	}

	public void setPropertyPrice(double propertyPrice) {
		this.propertyPrice = propertyPrice;
	}

	public String getNumberOfRooms() {
		return numberOfRooms;
	}

	public void setNumberOfRooms(String numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}

	public PropertyAddress getAddress() {
		return address;
	}

	public void setAddress(PropertyAddress addressEntity) {
		this.address = addressEntity;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}
}
