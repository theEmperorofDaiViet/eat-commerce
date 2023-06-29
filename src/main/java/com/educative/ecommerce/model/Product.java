package com.educative.ecommerce.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.educative.ecommerce.dto.product.ProductDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;
	
	public Product() {
		
	}
	
	public Product(@NotNull String name, @NotNull String description, @NotNull double price, @NotNull String imageUrl,
			Category category) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.imageUrl = imageUrl;
		this.category = category;
		this.isDisable = false;
	}
	
    public Product(ProductDTO productDTO, Category category) {
        this.name = productDTO.getName();
        this.imageUrl = productDTO.getImageUrl();
        this.description = productDTO.getDescription();
        this.price = productDTO.getPrice();
        this.category = category;
        this.isDisable = false;
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

	public boolean isDisable() {
		return isDisable;
	}

	public void setDisable(boolean isDisable) {
		this.isDisable = isDisable;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
