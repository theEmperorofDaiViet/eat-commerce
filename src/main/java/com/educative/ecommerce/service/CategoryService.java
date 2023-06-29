package com.educative.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educative.ecommerce.model.Category;
import com.educative.ecommerce.repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;

	public void createCategory(Category category) {
		categoryRepository.save(category);
	}
	
	public List<Category> getCategories(){
		return categoryRepository.findAll();
	}
	
	public Category getCategory(Integer id) {
		Optional<Category> optionalCategory = categoryRepository.findById(id);
		if(optionalCategory.isPresent()) {
			return optionalCategory.get();
		}
		return null;
	}
	
	public Category getCategory(String name) {
		Optional<Category> optionalCategory = categoryRepository.findByName(name);
		if(optionalCategory.isPresent()) {
			return optionalCategory.get();
		}
		return null;
	}
	
	public void updateCategory(Integer id, Category updateCategory) {
		Category category = categoryRepository.findById(id).get();
		category.setName(updateCategory.getName());
		category.setDescription(updateCategory.getDescription());
		category.setImageUrl(updateCategory.getImageUrl());
		
		categoryRepository.save(category);
	}
}
