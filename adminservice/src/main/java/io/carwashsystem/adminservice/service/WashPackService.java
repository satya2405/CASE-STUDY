package io.carwashsystem.adminservice.service;



import java.util.List;



import io.carwashsystem.adminservice.model.WashPacks;

public interface WashPackService {
	
	public String savepack(WashPacks pack);
	
	public List<WashPacks> getpack();
	
	public String deletepack( int id);

}
