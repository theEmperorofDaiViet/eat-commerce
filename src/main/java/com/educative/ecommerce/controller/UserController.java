package com.educative.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educative.ecommerce.dto.ResponseDTO;
import com.educative.ecommerce.dto.user.SignInDTO;
import com.educative.ecommerce.dto.user.SignInResponseDTO;
import com.educative.ecommerce.dto.user.SignUpDTO;
import com.educative.ecommerce.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/signup")
	public ResponseDTO signUp(@RequestBody SignUpDTO signUpDTO) {
		return userService.signUp(signUpDTO);
	}
	
	@PostMapping("/signin")
	public SignInResponseDTO signIn(@RequestBody SignInDTO signInDTO) {
		return userService.signIn(signInDTO);
	}
}
