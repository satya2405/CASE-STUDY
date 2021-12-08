package io.carwashsystem.userprofileservice.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.carwashsystem.userprofileservice.model.Userdetails;
import io.carwashsystem.userprofileservice.repo.UserRepository;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
      Userdetails user=repository.findByName(name);
       
      return new User(user.getName(),user.getPassword(),new ArrayList<>());
      
     // return new User("satya", "satya333",
          //    new ArrayList<>());
    	
    }
}