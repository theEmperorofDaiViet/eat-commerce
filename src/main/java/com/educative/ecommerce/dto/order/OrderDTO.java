package com.educative.ecommerce.dto.order;

import javax.validation.constraints.NotNull;

import com.educative.ecommerce.model.Order;

public class OrderDTO {

	private Integer id;
	
	@NotNull
	private Integer userId;
	
	public OrderDTO() {
		
	}
	
	public OrderDTO(Order order) {
		this.setId(order.getId());
		this.setUserId(order.getUser().getId());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
