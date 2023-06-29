package com.educative.ecommerce.exception;

public class AuthenticationFailedException extends IllegalArgumentException{
	
	public AuthenticationFailedException(String msg) {
		super(msg);
	}
}
