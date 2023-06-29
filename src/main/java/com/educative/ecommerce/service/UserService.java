package com.educative.ecommerce.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educative.ecommerce.dto.ResponseDTO;
import com.educative.ecommerce.dto.user.SignInDTO;
import com.educative.ecommerce.dto.user.SignInResponseDTO;
import com.educative.ecommerce.dto.user.SignUpDTO;
import com.educative.ecommerce.enums.Role;
import com.educative.ecommerce.exception.AuthenticationFailedException;
import com.educative.ecommerce.exception.CustomException;
import com.educative.ecommerce.model.AuthenticationToken;
import com.educative.ecommerce.model.User;
import com.educative.ecommerce.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@Transactional
	public ResponseDTO signUp(SignUpDTO signUpDTO) {
		if (userRepository.findByEmail(signUpDTO.getEmail()) != null) {
			throw new CustomException("User already exists!");
		}
		if (!Role.validate(signUpDTO.getRole())) {
			throw new CustomException("Role is not valid!");
		}
		
		String encryptedPassword = signUpDTO.getPassword();
		try {
			encryptedPassword = hashPassword(signUpDTO.getPassword());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		User user = new User(signUpDTO.getEmail(), encryptedPassword, signUpDTO.getFirstName(), signUpDTO.getLastName(),
							 signUpDTO.getDob(), signUpDTO.getPhone(), signUpDTO.getAddress(), signUpDTO.getRole());
				
		userRepository.save(user);
		
		final AuthenticationToken authenticationToken = new AuthenticationToken(user);
		authenticationService.saveConfirmationToken(authenticationToken);
		
		return new ResponseDTO("SUCCESS", signUpDTO.getRole() + " is created successfully!");
	}
	
	
	private String hashPassword(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(password.getBytes());
		byte[] digest = md.digest();
		String hash = DatatypeConverter.printHexBinary(digest).toUpperCase();
		return hash;
	}


	public SignInResponseDTO signIn(SignInDTO signInDTO) {
		User user = userRepository.findByEmail(signInDTO.getEmail());
		
		if (user == null) {
			throw new AuthenticationFailedException("User does not exist!");
		}
		
		try {
			if(!user.getPassword().equals(hashPassword(signInDTO.getPassword()))) {
				throw new AuthenticationFailedException("Wrong password!");
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		if (!signInDTO.getRole().equals(user.getRole())) {
			throw new CustomException("Forbidden!");
		}
		
		AuthenticationToken token = authenticationService.getToken(user);
		if (token == null) {
			throw new CustomException("Token is not presented!");
		}
		
		return new SignInResponseDTO("SUCCESS", token.getToken(), user.getRole());
	}
}
