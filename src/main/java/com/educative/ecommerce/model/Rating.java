package com.educative.ecommerce.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.educative.ecommerce.dto.rating.RatingDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Rating {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	private Integer score;
	
	private String description;
	
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
	
	private Date createdDate;
	
	public Rating() {
		
	}

	public Rating(@NotNull Integer score, String description, User user) {
		this.score = score;
		this.description = description;
		this.user = user;
		this.createdDate = new Date();
	}
	
	public Rating(RatingDTO ratingDTO, User user) {
		this.score = ratingDTO.getScore();
		this.description = ratingDTO.getDescription();
		this.createdDate = new Date();
		this.user = user;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
}
