package com.educative.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educative.ecommerce.dto.feedback.FeedbackDTO;
import com.educative.ecommerce.exception.ProductNotExistException;
import com.educative.ecommerce.model.Feedback;
import com.educative.ecommerce.model.Product;
import com.educative.ecommerce.model.User;
import com.educative.ecommerce.repository.FeedbackRepository;
import com.educative.ecommerce.repository.ProductRepository;

@Service
public class FeedbackService {

	@Autowired
	private FeedbackRepository feedbackRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	public void createFeedback(FeedbackDTO feedbackDTO, User user) {
		Optional<Product> optionalProduct = productRepository.findById(feedbackDTO.getProductId());
		if(!optionalProduct.isPresent()) {
			throw new ProductNotExistException("ProductId is not valid!");
		}
		Product product = optionalProduct.get();
		Feedback feedback = new Feedback(feedbackDTO, user, product);
		feedbackRepository.save(feedback);
	}
	
	public List<FeedbackDTO> getFeedbacks() {
		List<Feedback> feedbacks = feedbackRepository.findAll();
		List<FeedbackDTO> feedbackDTOs = new ArrayList<>();
		for(Feedback feedback: feedbacks) {
			feedbackDTOs.add(new FeedbackDTO(feedback));
		}
		return feedbackDTOs;
	}
	
	public FeedbackDTO getFeedback(Integer id) {
		Optional<Feedback> optionalFeedback = feedbackRepository.findById(id);
		if(optionalFeedback.isPresent()) {
			return new FeedbackDTO(optionalFeedback.get());
		}
		return null;
	}
	
	public void updateFeedback(Integer id, FeedbackDTO feedbackDTO, User user) {
		Optional<Product> optionalProduct = productRepository.findById(feedbackDTO.getProductId());
		if(!optionalProduct.isPresent()) {
			throw new ProductNotExistException("ProductId is not valid!");
		}
		Product product = optionalProduct.get();
		
		Feedback feedback = feedbackRepository.findById(id).get();
		feedback.setLiked(feedbackDTO.isLiked());
		feedback.setIssue(feedbackDTO.getIssue());
		feedback.setDescription(feedbackDTO.getDescription());
		feedback.setUser(user);
		feedback.setProduct(product);
		feedback.setCreatedDate(feedbackDTO.getCreatedDate());
	}
	
	public void deleteFeedback(Integer id) {
		feedbackRepository.deleteById(id);
	}
}
