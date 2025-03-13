package com.fds.payment.service;

import java.util.List;

import com.fds.payment.external.Order;
import com.fds.payment.model.Payment;

public interface PaymentService {
	Payment doPayment(Order o);
	Payment getPayment(int orderId);
    List<Payment> getallpayment();
    void changePaymentStatus(long id,Order o);
    void paymentSuccess(long id);
    String deletePayment(long id);

}
