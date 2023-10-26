package com.marolix.Bricks99.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.marolix.Bricks99.dto.UserRole;

@Entity
@Table(name = "user_backup_data")
public class UserBackUp {
	@Id
	private Integer user_id;
	private String email;
	private long phoneNumber;
	private String userName;
	private String password;
	@Enumerated(EnumType.STRING)
	private UserRole userRole;

	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
	    this.user_id = user_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
}
