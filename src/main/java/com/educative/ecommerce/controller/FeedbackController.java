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

import com.educative.ecommerce.dto.feedback.FeedbackDTO;
import com.educative.ecommerce.model.User;
import com.educative.ecommerce.service.AuthenticationService;
import com.educative.ecommerce.service.FeedbackService;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

	@Autowired
	private FeedbackService feedbackService;
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@PostMapping("/create")
	public ResponseEntity<FeedbackDTO> createFeedback(@RequestParam("token") String token, @RequestBody FeedbackDTO feedbackDTO) {
		authenticationService.authenticate(token);
		
		User user = authenticationService.getUser(token);
		feedbackService.createFeedback(feedbackDTO, user);
		return new ResponseEntity<>(feedbackDTO, HttpStatus.CREATED);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<FeedbackDTO>> getFeedbacks() {
		return new ResponseEntity<>(feedbackService.getFeedbacks(), HttpStatus.OK);
	}
	
	@PatchMapping("/update/{id}")
	public ResponseEntity<FeedbackDTO> updateFeedback(@RequestParam("token") String token, @PathVariable("id") Integer id, @RequestBody FeedbackDTO feedbackDTO) {
		authenticationService.authenticate(token);
		
		User user = authenticationService.getUser(token);	
		
		if(feedbackService.getFeedback(id) != null) {
			feedbackService.updateFeedback(id, feedbackDTO, user);
			return new ResponseEntity<>(feedbackDTO, HttpStatus.OK);
		}
		return new ResponseEntity<>((FeedbackDTO)null, HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteFeedback(@PathVariable("id") Integer id, @RequestParam("token") String token){
		feedbackService.deleteFeedback(id);
		return new ResponseEntity<>("Feedback has been deleted!", HttpStatus.OK);
	}
}
