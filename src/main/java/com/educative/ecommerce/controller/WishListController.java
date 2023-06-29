package com.educative.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.educative.ecommerce.service.AuthenticationService;
import com.educative.ecommerce.service.WishListService;
import com.educative.ecommerce.dto.product.ProductDTO;
import com.educative.ecommerce.model.Product;
import com.educative.ecommerce.model.User;
import com.educative.ecommerce.model.WishList;

@RestController
@RequestMapping("/wishlist")
public class WishListController {

	@Autowired
	private WishListService wishListService;
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@PostMapping("/add")
	public ResponseEntity<WishList> addWishList(@RequestBody Product product, @RequestParam("token") String token) {
		authenticationService.authenticate(token);
		
		User user = authenticationService.getUser(token);
		
		WishList wishList = new WishList(user, product);
		wishListService.addWishList(wishList);
		
		return new ResponseEntity<>(wishList, HttpStatus.CREATED);
	}
	
	@GetMapping("/{token}")
	public ResponseEntity<List<ProductDTO>> getWishList(@PathVariable("token") String token){
		authenticationService.authenticate(token);
		
		User user = authenticationService.getUser(token);
		
		List<ProductDTO> wishLists = wishListService.getWishList(user);
		return new ResponseEntity<>(wishLists, HttpStatus.OK);
	}
}
