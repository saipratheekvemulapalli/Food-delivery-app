package com.fds.payment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fds.payment.external.Order;
import com.fds.payment.model.Payment;
import com.fds.payment.service.PaymentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentController {
	
	@Autowired
	PaymentService paymentService;
	
	@PostMapping("/doPayment")
	public Payment doPayment(@RequestBody Order o){
		return paymentService.doPayment(o);
	}
	@GetMapping("/getByTransactionId/{orderId}")
	public ResponseEntity<Payment> getPayment(@PathVariable int orderId)  {
		Payment payment= paymentService.getPayment(orderId);
		return new ResponseEntity<Payment>(payment,HttpStatus.OK);
	}
	@GetMapping("/getAllPayment")
	public List<Payment> getAllPayments()
	{
		List<Payment> paymentlist=paymentService.getallpayment();
		return paymentlist;
	}
	
	@PutMapping("/updatePaymentFailed/{id}")
	public ResponseEntity<Void> updatePayment(@PathVariable int id,@RequestBody Order o) {
		log.info("In the updateFailedPaymetn method in controller");
		log.info("changing status to payment failed");
		paymentService.changePaymentStatus(id,o);

		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@PutMapping("/updatePaymentSuccess/{id}")
	public ResponseEntity<Void> updatePaymentToSuccess(@PathVariable int id) {
		log.info("In the updateFailedPaymetn method in controller");
		log.info("changing status to payment failed");
		paymentService.paymentSuccess(id);

		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@DeleteMapping("/deletePayment/{id}")
	public ResponseEntity<String> deletePayment(@PathVariable long id) {
		log.info("In the deletePayment method in controller");
		log.info("Deleting the payment");
		String s = paymentService.deletePayment(id);

		return ResponseEntity.status(HttpStatus.OK).body(s);

	}
		
	
	
	
}
