package io.carwashsystem.userprofileservice.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import io.carwashsystem.userprofileservice.model.Userdetails;

public interface UserRepository extends MongoRepository<Userdetails, Integer> {

	Userdetails findByName(String name);


}

