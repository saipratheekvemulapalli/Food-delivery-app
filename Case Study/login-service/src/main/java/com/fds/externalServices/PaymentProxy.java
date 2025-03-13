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
import com.fds.externalClass.Payment;

@FeignClient(name="payment-service",url = "http://localhost:9995/payment")
public interface PaymentProxy {
	@PostMapping("/doPayment")
	public Payment doPayment(@RequestBody Order o);
	
	@GetMapping("/getByTransactionId/{orderId}")
	public ResponseEntity<Payment> getPayment(@PathVariable int orderId) ;
	
	@GetMapping("/getAllPayment")
	public List<Payment> getAllPayments();
	
	@PutMapping("/updatePaymentFailed/{id}")
	public ResponseEntity<Void> updatePayment(@PathVariable int id,@RequestBody Order o) ;
	
	@PutMapping("/updatePaymentSuccess/{id}")
	public ResponseEntity<Void> updatePaymentToSuccess(@PathVariable int id);
	
	@DeleteMapping("/deletePayment/{id}")
	public ResponseEntity<String> deletePayment(@PathVariable long id);
}
