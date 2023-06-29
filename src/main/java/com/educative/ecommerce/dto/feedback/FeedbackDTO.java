package com.educative.ecommerce.dto.feedback;

import java.util.Date;

import com.educative.ecommerce.enums.Issue;
import com.educative.ecommerce.model.Feedback;

public class FeedbackDTO {

	private Integer id;
	
	private boolean isLiked;
	
	private Issue issue;
	
	private String description;
	
	private Integer userId;
	
	private Integer productId;
	
    private Date createdDate;
    
    public FeedbackDTO() {
    	
    }

	public FeedbackDTO(boolean isLiked, Issue issue, String description, Integer userId, Integer productId) {
		this.isLiked = isLiked;
		this.issue = issue;
		this.description = description;
		this.userId = userId;
		this.productId = productId;
		this.createdDate = new Date();
	}
	
	public FeedbackDTO(Feedback feedback) {
		this.setId(feedback.getId());
		this.setLiked(feedback.isLiked());
		this.setIssue(feedback.getIssue());
		this.setDescription(feedback.getDescription());
		this.setUserId(feedback.getUser().getId());
		this.setProductId(feedback.getProduct().getId());
		this.setCreatedDate(feedback.getCreatedDate());
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
}
