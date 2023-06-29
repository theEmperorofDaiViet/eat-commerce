package com.educative.ecommerce.dto.user;

import java.util.Date;

import com.educative.ecommerce.model.User;

public class UserDTO {

	private Integer id;
	
	private String email;
	
	private String firstName;
	
	private String lastName;
	
	private Date dob;
	
	private String phone;
	
	private String address;
	
	private String role;
	
	public UserDTO() {
		
	}
	
	public UserDTO(User user) {
		this.setId(user.getId());
		this.setEmail(user.getEmail());
		this.setFirstName(user.getFirstName());
		this.setLastName(user.getLastName());
		this.setDob(user.getDob());
		this.setPhone(user.getPhone());
		this.setAddress(user.getAddress());
		this.setRole(user.getRole());
	}

	public UserDTO(Integer id, String email, String firstName, String lastName, Date dob, String phone, String address,
			String role) {
		this.id = id;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.phone = phone;
		this.address = address;
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
