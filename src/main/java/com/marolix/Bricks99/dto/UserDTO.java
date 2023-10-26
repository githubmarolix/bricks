package com.marolix.Bricks99.dto;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.marolix.Bricks99.entity.UserType;

public class UserDTO {
	private Integer userId;
	private String firstName;
	private String lastName;
	private String email;
	private String contact;
	private LocalDate registeredAt;
	private String password;
	private Boolean contact_verified;
	private Boolean email_verified;
	
	private UserType userType;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public LocalDate getRegisteredAt() {
		return registeredAt;
	}

	public void setRegisteredAt(LocalDate registeredAt) {
		this.registeredAt = registeredAt;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getContact_verified() {
		return contact_verified;
	}

	public void setContact_verified(Boolean contact_verified) {
		this.contact_verified = contact_verified;
	}

	public Boolean getEmail_verified() {
		return email_verified;
	}

	public void setEmail_verified(Boolean email_verified) {
		this.email_verified = email_verified;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", contact=" + contact + ", registeredAt=" + registeredAt + ", password=" + password
				+ ", contact_verified=" + contact_verified + ", email_verified=" + email_verified + ", userType="
				+ userType + "]";
	}
	
	
}
