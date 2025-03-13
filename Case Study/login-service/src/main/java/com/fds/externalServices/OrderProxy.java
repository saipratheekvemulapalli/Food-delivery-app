package com.fds.externalServices;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fds.externalClass.Order;

@FeignClient(name="order-service",url = "http://localhost:9994/order")
public interface OrderProxy {
	
	@PostMapping("/order/{cartId}")
	ResponseEntity<String> makeAOrder(@RequestBody Order o,@PathVariable int cartId) ;

	@DeleteMapping("/cancelOrder/{id}")
	ResponseEntity<String> cancelOrder(@PathVariable int id) ;

	@PutMapping("/updateOrder/{id}")
	ResponseEntity<Order> updateAnOrder(@PathVariable int id);
	
	@GetMapping("/viewOrderById/{id}")
	Order viewAnOrder(@PathVariable int id) ;
	
	@GetMapping("/viewOrderByName/{orderName}")
	List<Order> viewByOrderName(@PathVariable String orderName);

	@GetMapping("/viewAllOrders")
	List<Order> viewAllOrder();
	
	@PutMapping("/updateFailedPayment/{id}")
	void updateFailedPayment(@PathVariable int id) ;
}
