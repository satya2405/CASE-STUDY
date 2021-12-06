package io.carwashsystem.orderservice.model;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Orderdetails")
public class OrderDetails {
	
	@Id
	@NotEmpty(message = "Order Id must not be empty")
	int orderId;
	
	@NotEmpty(message = "carName must not be empty")
	String carName;

	@NotEmpty(message = "car model must not be empty")
	String carModel;
	
	@NotEmpty(message = "Washer Name must not be empty")
	String washerName;

	@NotEmpty(message = "wash pack must not be empty")
	int washpackId;
	
	@NotEmpty(message = "phone no must not be empty")
	Long phoneNo;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public String getWasherName() {
		return washerName;
	}

	public void setWasherName(String washerName) {
		this.washerName = washerName;
	}

	public int getWashpackId() {
		return washpackId;
	}

	public void setWashpackId(int washpackId) {
		this.washpackId = washpackId;
	}

	public Long getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(Long phoneNo) {
		this.phoneNo = phoneNo;
	}

	@Override
	public String toString() {
		return "OrderDetails [orderId=" + orderId + ", carName=" + carName + ", carModel=" + carModel + ", washerName="
				+ washerName + ", washpackId=" + washpackId + ", phoneNo=" + phoneNo + "]";
	}

	
	
	
	
}

	
	