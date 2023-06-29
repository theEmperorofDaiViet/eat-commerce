package com.educative.ecommerce.controller;

import java.util.List;

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

import com.educative.ecommerce.dto.rating.RatingDTO;
import com.educative.ecommerce.model.User;
import com.educative.ecommerce.service.AuthenticationService;
import com.educative.ecommerce.service.RatingService;

@RestController
@RequestMapping("/rating")
public class RatingController {

	@Autowired
	private RatingService ratingService;
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@PostMapping("/create")
	public ResponseEntity<RatingDTO> createRating(@RequestParam("token") String token, @RequestBody RatingDTO ratingDTO) {
		authenticationService.authenticate(token);
		
		User user = authenticationService.getUser(token);
		ratingService.createRating(ratingDTO, user);
		return new ResponseEntity<>(ratingDTO, HttpStatus.CREATED);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<RatingDTO>> getRatings() {
		return new ResponseEntity<>(ratingService.getRatings(), HttpStatus.OK);
	}
	
	@PatchMapping("/update/{id}")
	public ResponseEntity<RatingDTO> updateRating(@RequestParam("token") String token, @PathVariable("id") Integer id, @RequestBody RatingDTO ratingDTO) {
		authenticationService.authenticate(token);
		
		User user = authenticationService.getUser(token);
		
		if(ratingService.getRating(id) != null) {
			ratingService.updateRating(id, ratingDTO, user);
			return new ResponseEntity<>(ratingDTO, HttpStatus.OK);
		}
		return new ResponseEntity<>((RatingDTO)null, HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteRating(@PathVariable("id") Integer id, @RequestParam("token") String token) {
		ratingService.deleteRating(id);
		return new ResponseEntity<>("Rating has been deleted!", HttpStatus.OK);
	}
}
