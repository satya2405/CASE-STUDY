package io.carwashsystem.washservice.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import io.carwashsystem.washservice.exception.ApiRequestException;
import io.carwashsystem.washservice.model.WasherDetails;
import io.carwashsystem.washservice.repo.WasherRepository;

public class WasherServiceImpl implements WasherService1{
	@Autowired
	private WasherRepository repository;
	
	@Override
	public WasherDetails addWasher(WasherDetails washer) {
		return repository.save(washer);
		
	}

	@Override
	public List<WasherDetails> getwashers() {
		List<WasherDetails> washers = repository.findAll();
		System.out.println("Getting data from DB : " + washers);
		return washers;
		
	}
	
	@Override
	public Optional<WasherDetails> getwasher(int id) 
	{
		return Optional.of(repository.findById(id).orElseThrow( () -> new ApiRequestException("WASHER NOT FOUND WITH THIS ID ::")));
		
	}

	@Override
	public List<WasherDetails> getwasherbylocation(String location) {
		return repository.findBylocation(location);
		
	}
	@Override
	public ResponseEntity<Object> deletewasher(int id)
	{
		boolean isUserExist=repository.existsById(id);
		if(isUserExist) {
			repository.deleteById(id);
			return new ResponseEntity<Object>("user deleted with id "+id,HttpStatus.OK);
		}
		else
		{
			throw new ApiRequestException("CAN NOT DELETE AS WASHER NOT FOUND WITH THIS ID ::");
		}
	}
	@Override
	public void deletewasher(WasherDetails washer) 
	{
	
		repository.delete(washer);
	}
	
	
}
	

