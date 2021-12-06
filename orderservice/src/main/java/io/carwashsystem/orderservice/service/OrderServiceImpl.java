package io.carwashsystem.orderservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import io.carwashsystem.orderservice.model.OrderDetails;
import io.carwashsystem.orderservice.repo.OrderRepository;

public class OrderServiceImpl implements OrderService1{

	@Autowired
	private OrderRepository repository;
	
	@Override
	public OrderDetails addorder(OrderDetails order) {
		return repository.save(order);
	}

	@Override
	public List<OrderDetails> getUsers() {
		List<OrderDetails> orders = repository.findAll();
		System.out.println("Getting data from DB : " + orders);
		return orders;
	}

	@Override
	public void deleteUser(OrderDetails user) {
		repository.delete(user);
		
	}

	@Override
	public void deleteById(int id) {
		repository.deleteById(id);
		
	}

	@Override
	public List<OrderDetails> getorder() {
		return repository.findAll();
	}

	@Override
	public boolean exists(int id) {

		return repository.existsById(id);
	}


	@Override
	public void update(OrderDetails order) {
		OrderDetails save= repository.findById(order.getOrderId())
				.orElseThrow(()->new RuntimeException(String.format("Cannot find customer by id %s",order.getOrderId())));
		
		save.setCarName(order.getCarName());
		save.setCarModel(order.getCarModel());
		save.setWasherName(order.getWasherName());
		save.setWashpackId(order.getWashpackId());
		save.setPhoneNo(order.getPhoneNo());
		
	}

}
