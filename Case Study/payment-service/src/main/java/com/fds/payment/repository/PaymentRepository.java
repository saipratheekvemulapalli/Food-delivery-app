package com.fds.payment.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.fds.payment.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

	
	Payment findByOrderId(int orderId);
	
	
	

}
