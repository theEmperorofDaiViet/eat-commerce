package com.educative.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educative.ecommerce.dto.product.ProductDTO;
import com.educative.ecommerce.model.Category;
import com.educative.ecommerce.service.CategoryService;
import com.educative.ecommerce.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/add")
	public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDTO){
		Category category = categoryService.getCategory(productDTO.getCategoryId());
		if(category != null) {
			productService.addProduct(productDTO, category);
			return new ResponseEntity<ProductDTO>(productDTO, HttpStatus.CREATED);
		}		
		return new ResponseEntity<ProductDTO>((ProductDTO)null, HttpStatus.CONFLICT);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<ProductDTO>> getProducts(){
		return new ResponseEntity<List<ProductDTO>>(productService.getProducts(), HttpStatus.OK);
	}
	
	@PatchMapping("/edit/{id}")
	public ResponseEntity<ProductDTO> editProduct(@PathVariable("id") Integer id, @RequestBody ProductDTO productDTO){
		Category category = categoryService.getCategory(productDTO.getCategoryId());
		if(category != null) {
			if(productService.getProduct(id) != null) {
				productService.editProduct(id, productDTO, category);
				return new ResponseEntity<ProductDTO>(productDTO, HttpStatus.OK);
			}
			return new ResponseEntity<ProductDTO>((ProductDTO)null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ProductDTO>((ProductDTO)null, HttpStatus.CONFLICT);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable("id") Integer id) {
		productService.deleteProduct(id);
		return new ResponseEntity<>("Product has been deleted!", HttpStatus.OK);
	}
}
