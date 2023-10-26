package com.marolix.Bricks99.dto;

import javax.validation.constraints.NotNull;

public class UserLoginDTO {
	private Integer user_id;
	private UserRole userRole;
	private String user_name;
	@NotNull(message = "{userlogin.password.empty}")
	private String password;
	private String email;
	private String contact;

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
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

	@Override
	public String toString() {
		return "UserLoginDTO [user_id=" + user_id + ", userRole=" + userRole + ", user_name=" + user_name
				+ ", password=" + password + "]";
	}

}
