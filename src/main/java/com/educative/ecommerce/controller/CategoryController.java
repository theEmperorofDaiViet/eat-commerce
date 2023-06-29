package com.educative.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educative.ecommerce.model.Category;
import com.educative.ecommerce.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/create")
	public ResponseEntity<Category> createCategory(@RequestBody Category category) {
		categoryService.createCategory(category);
		return new ResponseEntity<Category>(category, HttpStatus.CREATED);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Category>> getCategories() {
		return new ResponseEntity<List<Category>>(categoryService.getCategories(), HttpStatus.OK);
	}
	
	@PatchMapping("/update/{id}")
	public ResponseEntity<Category> updateCategory(@PathVariable("id") Integer id, @RequestBody Category category) {
		if(categoryService.getCategory(id) != null) {
			categoryService.updateCategory(id, category);
			return new ResponseEntity<Category>(category, HttpStatus.OK);
		}
		return new ResponseEntity<Category>((Category)null, HttpStatus.NOT_FOUND);
	}
}
