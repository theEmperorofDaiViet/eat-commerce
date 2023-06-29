package com.educative.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educative.ecommerce.dto.rating.RatingDTO;
import com.educative.ecommerce.model.Rating;
import com.educative.ecommerce.model.User;
import com.educative.ecommerce.repository.RatingRepository;

@Service
public class RatingService {

	@Autowired
	private RatingRepository ratingRepository;
	
	public void createRating(RatingDTO ratingDTO, User user) {
		Rating rating = new Rating(ratingDTO, user);
		ratingRepository.save(rating);
	}
	
	public List<RatingDTO> getRatings() {
		List<Rating> ratings = ratingRepository.findAll();
		List<RatingDTO> ratingDTOs = new ArrayList<>();
		for(Rating rating: ratings) {
			ratingDTOs.add(new RatingDTO(rating));
		}
		return ratingDTOs;
	}
	
	public RatingDTO getRating(Integer id) {
		Optional<Rating> optionalRating = ratingRepository.findById(id);
		if(optionalRating.isPresent()) {
			return new RatingDTO(optionalRating.get());
		}
		return null;
	}
	
	public void updateRating(Integer id, RatingDTO ratingDTO, User user) {
		Rating rating = ratingRepository.findById(id).get();
		rating.setScore(ratingDTO.getScore());
		rating.setDescription(ratingDTO.getDescription());
		rating.setCreatedDate(ratingDTO.getCreatedDate());
		rating.setUser(user);
		ratingRepository.save(rating);
	}
	
	public void deleteRating(Integer id) {
		ratingRepository.deleteById(id);
	}
}
