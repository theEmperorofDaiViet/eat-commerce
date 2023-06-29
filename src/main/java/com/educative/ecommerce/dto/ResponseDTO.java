package com.educative.ecommerce.dto;

public class ResponseDTO {

	private String status;
	
	private String message;
	
	public ResponseDTO() {
		
	}	

	public ResponseDTO(String status, String message) {
		this.status = status;
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
