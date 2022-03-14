package com.spring.tdd.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.tdd.model.BookingModel;

public interface BookingRepository extends JpaRepository<BookingModel, String>{

	Optional<BookingModel> findByReserveName(String name); 
	
}
