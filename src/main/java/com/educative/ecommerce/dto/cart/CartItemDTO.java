package com.educative.ecommerce.dto.cart;

import javax.validation.constraints.NotNull;

import com.educative.ecommerce.model.Cart;
import com.educative.ecommerce.model.Product;

public class CartItemDTO {

	private Integer id;
	
	@NotNull
	private Product product;
	
	@NotNull
	private Integer quantity;
	
	public CartItemDTO() {
		
	}
	
	public CartItemDTO(Cart cart) {
		this.setId(cart.getId());
		this.setProduct(cart.getProduct());
		this.setQuantity(cart.getQuantity());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		return "CartItemDTO { id = " + id + ", productName = " + product.getName() + ", quantity = " + quantity + " }";
	}
}
