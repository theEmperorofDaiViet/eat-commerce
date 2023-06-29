package com.educative.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.educative.ecommerce.model.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer>{

}
