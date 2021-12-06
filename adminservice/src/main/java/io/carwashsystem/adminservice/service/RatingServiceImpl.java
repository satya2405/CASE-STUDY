package io.carwashsystem.adminservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import io.carwashsystem.adminservice.model.Ratings;
import io.carwashsystem.adminservice.repo.RatingRepository;

public class RatingServiceImpl implements RatingService{

	@Autowired
	RatingRepository repo;
	@Override
	public String saverating(Ratings rating) {
		repo.save(rating);
		return " Thanks for Your Valuable feedback";
	
	}

	@Override
	public List<Ratings> getuser() {
		
		return repo.findAll();
	}

	

}
