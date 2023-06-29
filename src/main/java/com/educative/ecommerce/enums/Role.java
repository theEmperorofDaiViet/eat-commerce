package com.educative.ecommerce.enums;

public enum Role {
	
	CUSTOMER, MERCHANT, DRIVER, ADMIN;
	
	public static boolean validate(String value) {
		for(Role role: Role.values()) {
			if(role.name().equals(value)) {
				return true;
			}
		}
		return false;
	}
}
