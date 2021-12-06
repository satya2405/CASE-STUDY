package io.carwashsystem.adminservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import io.carwashsystem.adminservice.model.AdminDetails;
import io.carwashsystem.adminservice.repo.AdminRepository;

public class AdminServiceImpl implements AdminService {

	@Autowired 
	AdminRepository repo;
	@Override
	public String saveadmin(AdminDetails admin) {
		
		repo.save(admin);
		return " Admin saved successfully with id :"+admin.getId();
	}

	@Override
	public List<AdminDetails> getadmin() {
		
		return repo.findAll();
	}

	@Override
	public Optional<AdminDetails> getadmin(int id) {
		
		return repo.findById(id);
	}

	
	
}
