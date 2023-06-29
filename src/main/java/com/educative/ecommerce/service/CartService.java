package com.educative.ecommerce.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educative.ecommerce.dto.cart.AddToCartDTO;
import com.educative.ecommerce.dto.cart.CartDTO;
import com.educative.ecommerce.dto.cart.CartItemDTO;
import com.educative.ecommerce.exception.CartItemNotExistException;
import com.educative.ecommerce.exception.CustomException;
import com.educative.ecommerce.exception.ProductNotExistException;
import com.educative.ecommerce.model.Cart;
import com.educative.ecommerce.model.Product;
import com.educative.ecommerce.model.User;
import com.educative.ecommerce.repository.CartRepository;
import com.educative.ecommerce.repository.ProductRepository;

@Service
public class CartService {

	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	public void addToCart(AddToCartDTO addToCartDTO, User user) throws ProductNotExistException {
		Optional<Product> optionalProduct = productRepository.findById(addToCartDTO.getProductId());
		if(!optionalProduct.isPresent()) {
			throw new ProductNotExistException("ProductId is not valid!");
		}
		Product product = optionalProduct.get();
		
		Cart cart = new Cart(user, product, addToCartDTO.getQuantity());
		cartRepository.save(cart);
	}
	
	public void updateCartItem(AddToCartDTO addToCartDTO, User user) {
		Optional<Cart> optionalCart = cartRepository.findById(addToCartDTO.getId());
		if(!optionalCart.isPresent()) {
			throw new CartItemNotExistException("Cart Item is not exist!");
		}
		Cart cart = optionalCart.get();
		if(cart.getUser().getId() != user.getId()) {
			throw new CustomException("CartId is not valid!");
		}
		cart.setQuantity(addToCartDTO.getQuantity());
		cart.setCreatedDate(new Date());
		cartRepository.save(cart);
		
	}
	
	public CartDTO getCartItems(User user) {
		List<Cart> cartList = cartRepository.findAllByUserOrderByCreatedDateDesc(user);
		List<CartItemDTO> cartItems = new ArrayList<>();
		for(Cart cart: cartList) {
			CartItemDTO cartItemDTO = new CartItemDTO(cart);
			cartItems.add(cartItemDTO);
		}
		double totalCost = 0;
		for(CartItemDTO cartItemDTO: cartItems) {
			totalCost += (cartItemDTO.getProduct().getPrice() * cartItemDTO.getQuantity());
		}
		return new CartDTO(cartItems, totalCost);
	}
	
	public void removeCartItem(Integer id, User user) {
		Optional<Cart> optionalCart = cartRepository.findById(id);
		if(!optionalCart.isPresent()) {
			throw new CartItemNotExistException("Cart Item is not exist!");
		}
		if(optionalCart.get().getUser().getId() != user.getId()) {
			throw new CustomException("CartId is not valid!");
		}
		cartRepository.deleteById(id);
	}
	
	public void deleteCart(User user) {
		cartRepository.deleteByUser(user);
	}
}
