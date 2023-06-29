package com.educative.ecommerce.dto.rating;

import java.util.Date;

import com.educative.ecommerce.model.Rating;

public class RatingDTO {

	private Integer id;
	
	private Integer score;
	
	private String description;
	
	private Integer userId;
	
	private Date createdDate;
	
	public RatingDTO() {
		
	}
	
	public RatingDTO(Integer score, String description, Integer userId) {
		this.score = score;
		this.description = description;
		this.userId = userId;
	}

	public RatingDTO(Rating rating) {
		this.setId(rating.getId());
		this.setScore(rating.getScore());
		this.setDescription(rating.getDescription());
		this.setUserId(rating.getUser().getId());
		this.setCreatedDate(rating.getCreatedDate());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
}
