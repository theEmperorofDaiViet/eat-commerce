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

import com.educative.ecommerce.dto.checkout.CheckOutItemDTO;
import com.educative.ecommerce.dto.checkout.StripeResponse;
import com.educative.ecommerce.exception.AuthenticationFailedException;
import com.educative.ecommerce.exception.OrderNotFoundException;
import com.educative.ecommerce.model.Order;
import com.educative.ecommerce.model.User;
import com.educative.ecommerce.service.AuthenticationService;
import com.educative.ecommerce.service.OrderService;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/create-checkout-session")
	public ResponseEntity<StripeResponse> checkOutList(@RequestBody List<CheckOutItemDTO> checkOutItemDTOs) throws StripeException {
		Session session = orderService.createSession(checkOutItemDTOs);
		StripeResponse stripeResponse = new StripeResponse(session.getId());
		
		return new ResponseEntity<>(stripeResponse, HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<String> placeOrder(@RequestParam("token") String token, @RequestParam("sessionId") String sessionId) throws AuthenticationFailedException {
		authenticationService.authenticate(token);
		
		User user = authenticationService.getUser(token);
		
		orderService.placeOrder(user, sessionId);
		return new ResponseEntity<>("Order has been placed!", HttpStatus.CREATED);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Order>> getOrders(@RequestParam("token") String token) throws AuthenticationFailedException {
		authenticationService.authenticate(token);
		
		User user = authenticationService.getUser(token);
		
		return new ResponseEntity<>(orderService.getOrders(user), HttpStatus.OK);
	}
	
	@GetMapping("/get")
	public ResponseEntity<List<Order>> getOrders(){
		return new ResponseEntity<>(orderService.getOrders(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Order> getOrders(@PathVariable("id") Integer id, @RequestParam("token") String token) throws AuthenticationFailedException {
		authenticationService.authenticate(token);
		
		try {
			Order order = orderService.getOrder(id);
			return new ResponseEntity<>(order, HttpStatus.OK);
		} catch (OrderNotFoundException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
}
