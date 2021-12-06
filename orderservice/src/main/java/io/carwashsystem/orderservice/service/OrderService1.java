package io.carwashsystem.orderservice.service;

import java.util.List;


import org.springframework.stereotype.Service;

import io.carwashsystem.orderservice.model.OrderDetails;


@Service
public interface OrderService1 {
	public OrderDetails addorder(OrderDetails order);
	
	public List<OrderDetails> getUsers();
	
	public void deleteUser(OrderDetails user);
	
	public void deleteById(int id);
	
	public List<OrderDetails> getorder();
	
	public boolean exists(int id);
	
	public void update(OrderDetails order);
	

}
