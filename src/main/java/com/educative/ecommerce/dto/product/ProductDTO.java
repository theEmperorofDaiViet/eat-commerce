package com.educative.ecommerce.dto.product;

import javax.validation.constraints.NotNull;

import com.educative.ecommerce.model.Product;

public class ProductDTO {

	private Integer id;
	
	@NotNull
	private String name;
	
	@NotNull
	private String description;
	
	@NotNull
	private double price;

	@NotNull
	private String imageUrl;
	
	@NotNull
	private boolean isDisable;
	
	@NotNull
	private Integer categoryId;
	
	public ProductDTO() {
		
	}
	
    public ProductDTO(Product product) {
        this.setId(product.getId());
        this.setName(product.getName());
        this.setImageUrl(product.getImageUrl());
        this.setDescription(product.getDescription());
        this.setPrice(product.getPrice());
        this.setCategoryId(product.getCategory().getId());
        this.setDisable(product.isDisable());
    }

	public ProductDTO(@NotNull String name, @NotNull String description, @NotNull double price,
			@NotNull String imageUrl, @NotNull Integer categoryId, @NotNull boolean isDisabled) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.imageUrl = imageUrl;
		this.categoryId = categoryId;
		this.isDisable = isDisabled;
	}	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public boolean isDisable() {
		return isDisable;
	}

	public void setDisable(boolean isDisable) {
		this.isDisable = isDisable;
	}
	
	
}
