package com.educative.ecommerce.service;

import com.educative.ecommerce.dto.cart.CartDTO;
import com.educative.ecommerce.dto.cart.CartItemDTO;
import com.educative.ecommerce.dto.checkout.CheckOutItemDTO;
import com.educative.ecommerce.exception.OrderNotFoundException;
import com.educative.ecommerce.model.Order;
import com.educative.ecommerce.model.OrderItem;
import com.educative.ecommerce.model.User;
import com.educative.ecommerce.repository.OrderItemRepository;
import com.educative.ecommerce.repository.OrderRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import com.stripe.param.checkout.SessionCreateParams.LineItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

	@Value("${BASE_URL}")
	private String baseURL;
	
	@Value("${STRIPE_SECRET_KEY}")
	private String apiKey;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	public Session createSession(List<CheckOutItemDTO> checkOutItemDTOs) throws StripeException {
		String successURL = baseURL + "payment/success";
		String failedURL = baseURL + "payment/failed";
		
		Stripe.apiKey = apiKey;
		
		List<SessionCreateParams.LineItem> sessionItemList = new ArrayList<>();
		
		for(CheckOutItemDTO checkOutItemDTO: checkOutItemDTOs) {
			sessionItemList.add(createSessionItem(checkOutItemDTO));
		}
        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setCancelUrl(failedURL)
                .addAllLineItem(sessionItemList)
                .setSuccessUrl(successURL)
                .build();
        return Session.create(params);
	}

	private SessionCreateParams.LineItem createSessionItem(CheckOutItemDTO checkOutItemDTO) {
		// TODO Auto-generated method stub
		return SessionCreateParams.LineItem.builder()
				.setPriceData(createPriceData(checkOutItemDTO))
				.setQuantity(Long.parseLong(String.valueOf(checkOutItemDTO.getQuantity())))
				.build();
	}

	private SessionCreateParams.LineItem.PriceData createPriceData(CheckOutItemDTO checkOutItemDTO) {
		// TODO Auto-generated method stub
		return SessionCreateParams.LineItem.PriceData.builder()
				.setCurrency("vnd")
				.setUnitAmount((long)checkOutItemDTO.getPrice())
				.setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder().setName(checkOutItemDTO.getProductName()).build())
				.build();
	}

	public void placeOrder(User user, String sessionId) {
        CartDTO cartDTO = cartService.getCartItems(user);
        List<CartItemDTO> cartItemDTOs = cartDTO.getCartItems();
        
        Order newOrder = new Order(cartDTO.getTotalCost(), sessionId, user);
        orderRepository.save(newOrder);
        
        for(CartItemDTO cartItemDTO: cartItemDTOs) {
        	OrderItem orderItem = new OrderItem(cartItemDTO.getQuantity(), cartItemDTO.getProduct().getPrice(),
        									    newOrder, cartItemDTO.getProduct());
        	orderItemRepository.save(orderItem);
        }
        cartService.deleteCart(user);
	}
	
	public List<Order> getOrders() {
		return orderRepository.findAllByOrderByCreatedDateDesc();
	}
	
	public List<Order> getOrders(User user) {
		return orderRepository.findAllByUserOrderByCreatedDateDesc(user);
	}
	
	public Order getOrder(Integer id) {
		Optional<Order> optionalOrder = orderRepository.findById(id);
		if(!optionalOrder.isPresent()) {
			throw new OrderNotFoundException("Order not found!");
		}
		return optionalOrder.get();
	}
}
