package com.fds.order.service;

import java.util.List;

import com.fds.order.model.Order;

public interface OrderService {
	String makeOrder(Order o,int cartId);
	Order updateOrder(int id); 
	String cancelOrder(int id);
	Order getOrder(int id);
	List<Order> getOrderByUsername(String username);
	List<Order> getAllOrders() ;
	void orderPaymentFailed(int id);
}
