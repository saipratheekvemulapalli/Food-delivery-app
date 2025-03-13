package com.fds.order.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fds.order.model.Order;

public interface OrderRepo extends JpaRepository<Order, Integer>{

	List<Order> findByUsername(String orderName);
	
}
