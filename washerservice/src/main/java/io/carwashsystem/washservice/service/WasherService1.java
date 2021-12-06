package io.carwashsystem.washservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import io.carwashsystem.washservice.model.WasherDetails;

public interface WasherService1 {
	
	public WasherDetails addWasher(WasherDetails washer);
	
	public List<WasherDetails> getwashers();
	
	public List<WasherDetails> getwasherbylocation(String location);
	
	public void deletewasher(WasherDetails washer);

	public Optional<WasherDetails> getwasher(int id);

	public ResponseEntity<Object> deletewasher(int id);

}
