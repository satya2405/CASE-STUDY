package io.carwashsystem.adminservice.service;

import java.util.List;
import java.util.Optional;



import io.carwashsystem.adminservice.model.AdminDetails;

public interface AdminService {
	public String saveadmin(AdminDetails admin);
	
	public List<AdminDetails> getadmin();
	
	public Optional<AdminDetails> getadmin(int id);

	
	
	
	
	
}
