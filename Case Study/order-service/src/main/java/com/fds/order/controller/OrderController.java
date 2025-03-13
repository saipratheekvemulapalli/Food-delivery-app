package com.fds.order.controller;

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

import com.fds.order.model.Order;
import com.fds.order.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	OrderService os;

	@PostMapping("/order/{cartId}")
	public ResponseEntity<String> makeAOrder(@RequestBody Order o,@PathVariable int cartId) {
		log.info("In the makeAOrder method in controller");
		log.info("creating an order");
		String s = os.makeOrder(o,cartId);
		return ResponseEntity.status(HttpStatus.CREATED).body(s);
	}

	@DeleteMapping("/cancelOrder/{id}")
	public ResponseEntity<String> cancelOrder(@PathVariable int id) {
		log.info("In the cancelOrder method in controller");
		log.info("Cancelling an order");
		String s = os.cancelOrder(id);

		return ResponseEntity.status(HttpStatus.OK).body(s);

	}

	@PutMapping("/updateOrder/{id}")
	public ResponseEntity<Order> updateAnOrder(@PathVariable int id) {
		log.info("In the updateAnOrder method in controller");
		log.info("updating an order with status successfull");
		Order s = os.updateOrder(id);

		return ResponseEntity.status(HttpStatus.OK).body(s);
	}

	@GetMapping("/viewOrderById/{id}")
	public ResponseEntity<Order> viewAnOrder(@PathVariable int id) {
		log.info("In the viewAnOrder method in controller");
		log.info("viewing order by id");
		return ResponseEntity.status(HttpStatus.OK).body(os.getOrder(id));
	}
	
	@GetMapping("/viewOrderByName/{username}")
	public ResponseEntity<List<Order>> viewByOrderName(@PathVariable String username) {
		log.info("In the viewByOrderName method in controller");
		log.info("viewing order by username");
		return ResponseEntity.status(HttpStatus.OK).body(os.getOrderByUsername(username));
	}
	
	

	@GetMapping("/viewAllOrders")
	public ResponseEntity<List<Order>> viewAllOrder() {
		log.info("In the viewAllOrder method in controller");
		log.info("viewing all orders");
		return ResponseEntity.status(HttpStatus.OK).body(os.getAllOrders());
	}
	
	@PutMapping("/updateFailedPayment/{id}")
	public ResponseEntity<Void> updateFailedPayment(@PathVariable int id) {
		log.info("In the updateFailedPaymetn method in controller");
		log.info("changing status to payment failed");
		os.orderPaymentFailed(id);

		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
