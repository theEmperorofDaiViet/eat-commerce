package com.educative.ecommerce.model;

import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	@NotBlank
	private String name;
	
	@Column
	@NotBlank
	private String description;
	
	@Column
	@NotBlank
	private String imageUrl;
	
	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Product> products;
	
	public Category() {
		
	}

	public Category(@NotBlank String name, @NotBlank String description, @NotBlank String imageUrl) {
		this.name = name;
		this.description = description;
		this.imageUrl = imageUrl;
	}

	public Category(@NotBlank String name, @NotBlank String description) {
		this.name = name;
		this.description = description;
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

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	
	@Override
	public String toString() {
		return "Category { id=" + id + ", name='" + name + "', description='" + description + "' }";
	}
}
