package io.carwashsystem.orderservice.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.carwashsystem.orderservice.exception.ApiRequestException;
import io.carwashsystem.orderservice.model.OrderDetails;


import io.carwashsystem.orderservice.service.OrderService1;


@RequestMapping(value="order")
@RestController
public class OrderController {
	
	
	
	@Autowired
	private OrderService1 service;
	
	@PostMapping("/addorder")
	 public String addorder( @RequestBody OrderDetails order) {
	 	service.addorder(order);
	 	return "Order is Placed with Washer and will be Proceesed soon "
	 			+order;
	 }
	
	@GetMapping("/allorders")
	public List<OrderDetails> getorder()
	{
		return  service.getorder(); 
	}
	@PutMapping("/updateorder/{id}")
	public  ResponseEntity<Object> update(@RequestBody OrderDetails order) {
		service.update(order);
		return ResponseEntity.ok().build();
	}
	
	 @DeleteMapping("/delete/{id}")
	 public ResponseEntity<Object> deletorder(@PathVariable int id)
	 {
		 boolean isOrderExist=service.exists(id);              
		 if(isOrderExist) {
			 service.deleteById(id);
			 return new ResponseEntity<Object>("Order deleted with id "+id,HttpStatus.OK);
		 }
		 else
		 {
			 throw new ApiRequestException("CAN NOT DELETE ORDER,AS ORDER NOT FOUND WITH THIS ID ::");
		 }
	 }
			
}
