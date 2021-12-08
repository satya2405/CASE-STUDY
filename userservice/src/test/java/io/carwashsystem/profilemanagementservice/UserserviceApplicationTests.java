package io.carwashsystem.profilemanagementservice;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import io.carwashsystem.userprofileservice.model.Userdetails;
import io.carwashsystem.userprofileservice.repo.UserRepository;
import io.carwashsystem.userprofileservice.service.UserService1;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserserviceApplicationTests  {

	@Autowired
	private UserService1 service;

	@MockBean
	private UserRepository repository;

	@Test
	public void getUsersTest() {
		when(repository.findAll()).thenReturn(Stream
				.of(new Userdetails(37, "Danile","USA","dany123"),
						new Userdetails(95, "Huy","UK","sfgt123")).collect(Collectors.toList()));
		assertEquals(2, service.getUsers().size());
	}

	@Test
	public void saveUserTest() {
		Userdetails user = new Userdetails(999, "Pranya","Pune","pranu345");
		when(repository.save(user)).thenReturn(user);
		assertEquals(user, service.addUser(user));
	}

	//@Test
	//public void deleteUserTest() {
	//	Userdetails user = new Userdetails(999, "Pranya","Pune","pranu345");	
	//	service.deleteById(int id);
	//	verify(repository, times(1)).delete(id);
	//}

}
