package com.marolix.Bricks99.dto;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.marolix.Bricks99.entity.SellerAddress;
import com.marolix.Bricks99.entity.Seller;
import com.marolix.Bricks99.entity.SellerStatus;
import com.marolix.Bricks99.utility.HashingUtility;

public class SellerDTO {

	private Integer sellerId;
	@NotNull(message = "{seller.fname.empty}")
	@Pattern(regexp = "([A-Za-z]+)([ ][A-Z][a-z]+)*", message = "{seller.fname.invalid}")
	private String firstName;
	@NotNull(message = "{seller.lname.empty}")
	@Pattern(regexp = "([A-Za-z]+)([ ][A-Z][a-z]+)*", message = "{seller.lname.invalid}")
	private String lastName;
	@Email(message = "{seller.email.invalid}")
	@NotNull(message = "{seller.email.empty}")
	private String email;
	@NotNull(message = "{seller.contact.null}")
	private String contact;
	@NotNull(message = "{seller.password.null}")
	private String password;
	@NotNull(message = "{seller.adhaar.null}")
	private String adhaar;

	public String getAdhaar() {
		return adhaar;
	}

	public void setAdhaar(String adhaar) {
		this.adhaar = adhaar;
	}

	@Valid
	private SellerAddressDTO address;
	private SellerStatus status;

	public SellerStatus getStatus() {
		return status;
	}

	public void setStatus(SellerStatus status) {
		this.status = status;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public SellerAddressDTO getAddress() {
		return address;
	}

	public void setAddress(SellerAddressDTO address) {
		this.address = address;
	}

	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	public static SellerDTO entityToDTO(Seller p) {
		SellerDTO dto = new SellerDTO();
		dto.setSellerId(p.getSellerId());
		dto.setFirstName(p.getFirstName());
		dto.setLastName(p.getLastName());
		dto.setContact(p.getContact());
		dto.setEmail(p.getEmail());
		
		dto.setStatus(p.getStatus());
		SellerAddressDTO aDTO = new SellerAddressDTO();
		SellerAddress a = p.getAddress();
		aDTO.setAddressLine1(a.getAddressLine1());
		aDTO.setAddressLine2(a.getAddressLine2());
		aDTO.setCity(a.getCity());
		aDTO.setPincode(a.getPincode());
		aDTO.setState(a.getState());
		dto.setAddress(aDTO);
		return dto;

	}

	public static Seller dtoToEntity(SellerDTO sellerDTO) {

		Seller newSR = new Seller();
		newSR.setContact(sellerDTO.getContact());
		newSR.setEmail(sellerDTO.getEmail());
		
		newSR.setFirstName(sellerDTO.getFirstName());
		newSR.setLastName(sellerDTO.getLastName());
		newSR.setStatus(SellerStatus.PENDING);
		String s = HashingUtility.hashedString(sellerDTO.getPassword());
		newSR.setPassword(s);
		SellerAddress address = new SellerAddress();
		SellerAddressDTO adto = sellerDTO.getAddress();
		address.setAddressLine1(adto.getAddressLine1());
		address.setAddressLine2(adto.getAddressLine2());
		address.setCity(adto.getCity());
		address.setState(adto.getState());
		address.setPincode(adto.getPincode());
		newSR.setAddress(address);
		return newSR;
	}

	@Override
	public String toString() {
		return "SellerRegistrationDTO [sellerId=" + sellerId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", contact=" + contact + ", password=" + password + ", adhaar=" + adhaar
				+ ", address=" + address + ", status=" + status + "]";
	}

}
