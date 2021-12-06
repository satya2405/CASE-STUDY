package io.carwashsystem.washerservice;


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

import io.carwashsystem.washservice.model.WasherDetails;
import io.carwashsystem.washservice.repo.WasherRepository;
import io.carwashsystem.washservice.service.WasherService1;


@RunWith(SpringRunner.class)
@SpringBootTest
public class WasherserviceApplicationTests  {

	@Autowired
	private WasherService1 service;

	@MockBean
	private WasherRepository repository;

	@Test
	public void getUsersTest() {
		when(repository.findAll()).thenReturn(Stream
				.of(new WasherDetails(37, "suresh","India","vk123"),
						new WasherDetails(95, "thimmesh","UK","sfgt123")).collect(Collectors.toList()));
		assertEquals(2, service.getwashers().size());
	}

	@Test
	public void getUserbyAddressTest() {
		String location="cc";
		when(repository.findBylocation(location))
				.thenReturn(Stream.of(new WasherDetails(37, "ramesh","USA","dany123"))
						.collect(Collectors.toList()));
		assertEquals(1, service.getwasherbylocation(location).size());
	}

	@Test
	public void saveUserTest() {
		WasherDetails washer = new WasherDetails(999, "sanju","manglore","sanju123");
		when(repository.save(washer)).thenReturn(washer);
		assertEquals(washer, service.addWasher(washer));
	}

	@Test
	public void deleteUserTest() {
		WasherDetails washer = new WasherDetails(999, "hareesh","manglore","hari123");	
		service.deletewasher(washer);
		verify(repository, times(1)).delete(washer);
	}

}
