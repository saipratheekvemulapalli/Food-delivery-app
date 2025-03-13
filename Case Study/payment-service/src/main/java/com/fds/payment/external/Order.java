package com.fds.payment.external;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
	private int orderId;
	private Date orderDate;
	private String username;
	private String orderName;
	private String orderStatus;
	private List<String> orderDetails;
	private String  phoneNumber;
	private double cost;
	private String address;
	private String pincode;
	private String city;
	private String state;
	private String orderInstructions;

}
