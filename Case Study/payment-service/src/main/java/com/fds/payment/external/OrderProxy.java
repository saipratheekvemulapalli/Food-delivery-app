package com.fds.payment.external;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@FeignClient(name = "order-service" ,url = "http://localhost:9999/order")
public interface OrderProxy {
	
	@GetMapping("/viewByName/{orderName}")
	public List<Order> viewByOrderName(@PathVariable String orderName);

//	@GetMapping("/viewByName/{orderName}")
//	List<Order> viewByOrderName(@PathVariable String orderName) {
//
//		return os.getOrderByOrderName(orderName);
//	}
	@GetMapping("/viewAllOrders")
	public ResponseEntity<List<Order>> viewAllOrder();
}
