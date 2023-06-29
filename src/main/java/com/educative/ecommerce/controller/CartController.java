package com.educative.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.educative.ecommerce.dto.cart.AddToCartDTO;
import com.educative.ecommerce.dto.cart.CartDTO;
import com.educative.ecommerce.model.User;
import com.educative.ecommerce.service.AuthenticationService;
import com.educative.ecommerce.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@PostMapping("/add")
	public ResponseEntity<AddToCartDTO> addToCart(@RequestBody AddToCartDTO addToCartDTO, @RequestParam("token") String token){
		authenticationService.authenticate(token);
		
		User user = authenticationService.getUser(token);
		
		cartService.addToCart(addToCartDTO, user);
		return new ResponseEntity<>(addToCartDTO, HttpStatus.CREATED);
	}
	
	@PatchMapping("/update/{id}")
	public ResponseEntity<AddToCartDTO> updateCartItem(@RequestBody AddToCartDTO addToCartDTO, @RequestParam("token") String token){
		authenticationService.authenticate(token);
		
		User user = authenticationService.getUser(token);
		
		cartService.updateCartItem(addToCartDTO, user);
		return new ResponseEntity<>(addToCartDTO, HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<CartDTO> getCartItems(@RequestParam("token") String token){
		authenticationService.authenticate(token);
		
		User user = authenticationService.getUser(token);
		
		return new ResponseEntity<>(cartService.getCartItems(user), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> removeCartItem(@PathVariable("id") Integer id, @RequestParam("token") String token){
		authenticationService.authenticate(token);
		
		User user = authenticationService.getUser(token);
		
		cartService.removeCartItem(id, user);
		return new ResponseEntity<>("Item has been removed!", HttpStatus.OK);
	}
}
