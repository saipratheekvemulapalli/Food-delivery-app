package com.fds.payment.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fds.payment.exception.PaymentException;
import com.fds.payment.external.Order;
import com.fds.payment.external.OrderProxy;
import com.fds.payment.model.Payment;
import com.fds.payment.repository.PaymentRepository;

import lombok.extern.slf4j.Slf4j;



@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	PaymentRepository paymentRepository;
	
	@Autowired
	OrderProxy orderProxy;
	
	
	@Override
	public Payment doPayment(Order o) {
		Payment payment = null;
      try {
          payment = new Payment();
          Random random = new Random();
          
          // Generate a random number between 100 and 1000 (inclusive)
          int randomNumber = random.nextInt(900000001) + 1000000;
          System.out.println(randomNumber);
          String tractionId = "" + randomNumber;
          int id = Integer.parseInt(tractionId);
          payment.setPaymentDate(new Date());
          payment.setTransactionId(id);
          payment.setUsername(o.getUsername());
          payment.setAmount(o.getCost());
          payment.setTransactionStatus(o.getOrderStatus());
          payment.setOrderId(o.getOrderId());
          return paymentRepository.save(payment);

      } catch (Exception e) {
          // TODO: handle exception

          throw new PaymentException("Payment Failed of RS " +payment.getAmount());

      }
	}

	@Override
	public Payment getPayment(int orderId) {
		 Payment optionalPayment = paymentRepository.findByOrderId(orderId);

	        if (optionalPayment!=null) {

	            return optionalPayment;

	        } else {
	            throw new PaymentException("Payment not found with transactionId " + orderId);
	        }
	}

	@Override
	public List<Payment> getallpayment() {
		List<Payment> p=paymentRepository.findAll();
    	return p;
	}

	@Override
	public void changePaymentStatus(long id,Order o) {
		// TODO Auto-generated method stub
		Optional<Payment> payment= paymentRepository.findById( id);
		
		if(payment.isPresent()) {
			Payment p1=payment.get();
			log.info("status is payment failed");
			p1.setTransactionStatus(o.getOrderStatus());
			paymentRepository.save(p1);
			
		}
	}

	@Override
	public void paymentSuccess(long id) {
		// TODO Auto-generated method stub
		Optional<Payment> payment= paymentRepository.findById(id);
		if(payment.isPresent()) {
			Payment p1=payment.get();
			log.info("status is payment success");
			p1.setTransactionStatus("Payment Done");
			paymentRepository.save(p1);
			
		}
	}

	@Override
	public String deletePayment(long id) {
		log.info("deletePayment method is started");
		// TODO Auto-generated method stub
		Optional<Payment> payment = paymentRepository.findById(id);
		
		if (payment.isPresent()) {
		paymentRepository.deleteById(id);
		return "Payment is deleted Successfully";
		}
		else {
			throw new PaymentException("The Payment with "+id+" is not exists");
		}
	}

}
