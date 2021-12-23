package io.carwashsystem.userprofileservice.controller;


import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.common.base.Optional;

import io.carwashsystem.userprofileservice.exception.ApiRequestException;
import io.carwashsystem.userprofileservice.model.AuthenticationRequest;
import io.carwashsystem.userprofileservice.model.AuthenticationResponse;
import io.carwashsystem.userprofileservice.model.OrderDetails;
import io.carwashsystem.userprofileservice.model.PaymentDetails;
import io.carwashsystem.userprofileservice.model.Ratings;
import io.carwashsystem.userprofileservice.model.Userdetails;
import io.carwashsystem.userprofileservice.model.WashPacks;
import io.carwashsystem.userprofileservice.repo.UserRepository;
import io.carwashsystem.userprofileservice.service.MyUserDetailsService;
import io.carwashsystem.userprofileservice.service.UserService1;
import io.carwashsystem.userprofileservice.util.JwtUtil;

@RestController
@RequestMapping("/user/")
public class ProfileController {
	
	
	@Autowired
	private UserService1 service;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private RestTemplate restTemplate;

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
	
	 @PostMapping(value ="/adduser")
	 public Userdetails saveUser(@Valid @RequestBody Userdetails user) {
	 	return service.addUser(user);
	 }
 
	 @GetMapping("/allusers")
	 public List<Userdetails> findAllUsers() {
	 	return service.getUsers();
	 }
	
	 @PutMapping("/update/{id}")
	 public ResponseEntity<Object> updateuser(@PathVariable int id,  @RequestBody Userdetails user )
	 {
		 return service.updateuser(id,user);
	 	
	 }
	
	 @GetMapping("/allusers/{id}")
	 public Optional<Userdetails> getuser(@PathVariable int id)
	 		throws ApiRequestException
	 {
		 return service.getuser(id);
		
	 }
	
	 @DeleteMapping("/delete/{id}")
	 public ResponseEntity<Object> deleteById(@PathVariable int id)
	 {
		 return service.deleteById(id);
		
	 }
	
	 @GetMapping("/allpacks")
	 public List<WashPacks> getwashpacks(){
		 String baseurl="http://localhost:8091/admin/allpacks";
		 WashPacks[] washPacks=restTemplate.getForObject(baseurl, WashPacks[].class);
		return Arrays.asList(washPacks);
	 }

	 @PostMapping("/payment")
	 public String payment(@RequestBody PaymentDetails payment) {
		  HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<PaymentDetails> entity = new HttpEntity<PaymentDetails>(payment,headers);
	      
	      return restTemplate.exchange(
	          "http://localhost:9191/payment/", HttpMethod.POST, entity, String.class).getBody();
	   }
		
		@PostMapping("/addrating")
		  public String addrating(@RequestBody Ratings rating) {
	      HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<Ratings> entity = new HttpEntity<Ratings>(rating,headers);
	      
	      return restTemplate.exchange(
	         "http://localhost:8091/admin/addrating", HttpMethod.POST, entity, String.class).getBody();
	    }
		
		@PostMapping("/addorder")
		 public String addorder(@RequestBody OrderDetails order) {
	     HttpHeaders headers = new HttpHeaders();
	     headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	     HttpEntity<OrderDetails> entity = new HttpEntity<OrderDetails>(order,headers); 
	     return restTemplate.exchange(
	          "http://localhost:8087/order/addorder", HttpMethod.POST, entity, String.class).getBody();
	   }
	

		 @DeleteMapping("/cancelorder")
		 public String deleteorder(){
			 String baseurl="http://localhost:8087/delete/";
			 OrderDetails order=restTemplate.getForObject(baseurl, OrderDetails.class);
			return "Your Order is successfully Canceled "+order;
		 }

	
}