package com.educative.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educative.ecommerce.dto.product.ProductDTO;
import com.educative.ecommerce.model.Category;
import com.educative.ecommerce.model.Product;
import com.educative.ecommerce.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public void addProduct(ProductDTO productDTO, Category category) {
		Product product = new Product(productDTO, category);
		productRepository.save(product);
	}
	
	public List<ProductDTO> getProducts() {
		List<Product> products = productRepository.findAll();
		List<ProductDTO> productDTOs = new ArrayList<>();
		for(Product product: products) {
			productDTOs.add(new ProductDTO(product));
		}
		return productDTOs;
	}
	
	public ProductDTO getProduct(Integer id) {
		Optional<Product> optionalProduct = productRepository.findById(id);
		if(optionalProduct.isPresent()) {
			return new ProductDTO(optionalProduct.get());
		}
		return null;
		
	}
	
	public void editProduct(Integer id, ProductDTO productDTO, Category category) {
		Product product = productRepository.findById(id).get();
		product.setName(productDTO.getName());
		product.setDescription(productDTO.getDescription());
		product.setPrice(productDTO.getPrice());
		product.setImageUrl(productDTO.getImageUrl());
		product.setCategory(category);
		
		productRepository.save(product);
	}
	
	public void deleteProduct(Integer id) {
		Product product = productRepository.findById(id).get();
		product.setDisable(true);
		productRepository.save(product);
	}
}
