package com.educative.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educative.ecommerce.exception.AuthenticationFailedException;
import com.educative.ecommerce.model.AuthenticationToken;
import com.educative.ecommerce.model.User;
import com.educative.ecommerce.repository.TokenRepository;

@Service
public class AuthenticationService {

	@Autowired
	private TokenRepository tokenRepository;
	
	public void saveConfirmationToken(AuthenticationToken authenticationToken) {
		tokenRepository.save(authenticationToken);
	}
	
	public AuthenticationToken getToken(User user) {
		return tokenRepository.findByUser(user);
	}
	
	public User getUser(String token) {
		AuthenticationToken authenticationToken = tokenRepository.findByToken(token);
		if (authenticationToken == null) {
			return null;
		}
		return authenticationToken.getUser();
	}
	
	public void authenticate(String token) throws AuthenticationFailedException {
		if (token == null) {
			throw new AuthenticationFailedException("Token is not presented!");
		}
		if (getUser(token) == null) {
			throw new AuthenticationFailedException("Token is not valid!");
		}
	}
}
