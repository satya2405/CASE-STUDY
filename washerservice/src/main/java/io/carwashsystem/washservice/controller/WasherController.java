package io.carwashsystem.washservice.controller;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.carwashsystem.washservice.exception.ApiRequestException;
import io.carwashsystem.washservice.model.AuthenticationRequest;
import io.carwashsystem.washservice.model.AuthenticationResponse;
import io.carwashsystem.washservice.model.OrderDetails;
import io.carwashsystem.washservice.model.Ratings;
import io.carwashsystem.washservice.model.WasherDetails;
import io.carwashsystem.washservice.repo.WasherRepository;
import io.carwashsystem.washservice.service.MyUserDetailsService;
import io.carwashsystem.washservice.service.WasherService1;
import io.carwashsystem.washservice.util.JwtUtil;


@RestController
@RequestMapping("/washer")
public class WasherController {
	
	
	@Autowired
	private WasherService1 service;
	
	
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
			);
		}
		catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}


		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	
	
	
	
	//admin can add washer details through admin service
	
	@PostMapping(value = "/addwasher")
	public WasherDetails saveWasher(@Valid @RequestBody WasherDetails washer) {
		return service.addWasher(washer);
	}

	
	@GetMapping("/allwashers")
	public List<WasherDetails> findAllwashers() {
		return service. getwashers();
	}
  
	
	@GetMapping("/allwashers/{location}")
	public List<WasherDetails> findwasherByAddress(@PathVariable String location) {
		return service.getwasherbylocation(location);
	}

	@DeleteMapping(value="/delete")
	public WasherDetails removeWaser(@RequestBody WasherDetails washer) {
		service.deletewasher(washer);
		return washer;
	}
	@GetMapping("/allwashers/{id}")
	public Optional<WasherDetails> getwasher(@PathVariable int id)
		throws ApiRequestException
	{
		return service.getwasher(id);
				
	}
	
	
	
	
	
	
	@GetMapping("/allratings")
	public List<Ratings> getallratings(){
		String baseurl="http://localhost:8091/admin/allratings";
		Ratings[] allratings=restTemplate.getForObject(baseurl, Ratings[].class);
		
		return Arrays.asList(allratings);
	}

	@GetMapping("/allorders")
	public List<Object> getallorders(){
		String baseurl="http://localhost:8087/order/allorders";
		Object[] allorders=restTemplate.getForObject(baseurl, Object[].class);
		
		return Arrays.asList(allorders);
	}
	
	

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deletewasher(@PathVariable int id)
	{
		return service.deletewasher(id);
		
		
	}

}
