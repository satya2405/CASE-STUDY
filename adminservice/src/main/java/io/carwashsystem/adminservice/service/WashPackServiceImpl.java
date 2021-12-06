package io.carwashsystem.adminservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import io.carwashsystem.adminservice.model.WashPacks;
import io.carwashsystem.adminservice.repo.WashPackRepository;

public class WashPackServiceImpl implements WashPackService {

	@Autowired
	WashPackRepository repo;
	@Override
	public String savepack(WashPacks pack) {
		
		
		repo.save(pack);
		return " Pack saved successfully with id :"+pack.getId();
	}

	@Override
	public List<WashPacks> getpack() {
		
		return repo.findAll();
	}

	@Override
	public String deletepack(int id) {
		
		
		repo.deleteById(id);
		return "pack deleted with id "+id;
	}

}
