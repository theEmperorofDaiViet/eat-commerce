package com.educative.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educative.ecommerce.model.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer>{

}
