package com.educative.ecommerce.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.educative.ecommerce.dto.feedback.FeedbackDTO;
import com.educative.ecommerce.enums.Issue;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Feedback {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	private boolean isLiked;
	
	private Issue issue;
	
	private String description;
	
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
	
    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
	private Product product;
    
    private Date createdDate;
	
	public Feedback() {
		
	}

	public Feedback(@NotNull boolean isLiked, Issue issue, String description, User user, Product product) {
		this.isLiked = isLiked;
		this.issue = issue;
		this.description = description;
		this.user = user;
		this.product = product;
		this.createdDate = new Date();
	}
	
	public Feedback(FeedbackDTO feedbackDTO, User user, Product product) {
		this.isLiked = feedbackDTO.isLiked();
		this.issue = feedbackDTO.getIssue();
		this.description = feedbackDTO.getDescription();
		this.user = user;
		this.product = product;
		this.createdDate = new Date();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isLiked() {
		return isLiked;
	}

	public void setLiked(boolean isLiked) {
		this.isLiked = isLiked;
	}

	public Issue getIssue() {
		return issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
}
