package com.educative.ecommerce.dto.user;

public class SignInResponseDTO {

	private String status;
	
	private String token;
	
	private String role;
	
	public SignInResponseDTO() {
		
	}

	public SignInResponseDTO(String status, String token, String role) {
		this.status = status;
		this.token = token;
		this.role = role;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
