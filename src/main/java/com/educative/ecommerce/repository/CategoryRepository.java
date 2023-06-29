package com.educative.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educative.ecommerce.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

	Optional<Category> findByName(String name);

}
