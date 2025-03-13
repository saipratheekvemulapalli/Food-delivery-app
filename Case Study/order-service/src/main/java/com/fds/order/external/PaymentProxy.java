package com.fds.order.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fds.order.model.Order;


@FeignClient(name = "payment-service", url="http://localhost:9995/payment")
public interface PaymentProxy {
	
	
	
	@PostMapping("/doPayment")
	public Payment doPayment(@RequestBody Order o);
	
	@GetMapping("/getByTransactionId/{orderId}")
	public ResponseEntity<Payment> getPayment(@PathVariable int orderId);
	
	@PutMapping("/updatePaymentFailed/{id}")
	public void updatePayment(@PathVariable long id,@RequestBody Order o);

	@PutMapping("/updatePaymentSuccess/{id}")
	public ResponseEntity<Void> updatePaymentToSuccess(@PathVariable long id);
}
