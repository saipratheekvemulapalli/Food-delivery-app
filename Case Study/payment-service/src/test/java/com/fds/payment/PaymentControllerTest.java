package com.fds.payment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fds.payment.controller.PaymentController;
import com.fds.payment.external.Order;
import com.fds.payment.model.Payment;
import com.fds.payment.service.PaymentService;

public class PaymentControllerTest {

    @InjectMocks
    private PaymentController paymentController;

    @Mock
    private PaymentService paymentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDoPayment() {
        Payment payment = new Payment(1l, 1,new Date(), "User123", 100.0, "Success");
        Order o = new Order();
        when(paymentService.doPayment(o)).thenReturn(payment);

        Payment result = paymentController.doPayment(o);

        assertEquals(payment, result);
    }

    @Test
    public void testGetPayment() {
        Payment payment = new Payment(1, 1, new Date(),"User123", 100.0, "Success");
        when(paymentService.getPayment(1)).thenReturn(payment);

        ResponseEntity<Payment> response = paymentController.getPayment(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(payment, response.getBody());
    }

    @Test
    public void testGetAllPayments() {
        List<Payment> paymentList = new ArrayList<>();
        paymentList.add(new Payment(1, 1,new Date(), "User1", 100.0, "Success"));
        paymentList.add(new Payment(2,2,new Date(), "User2", 200.0, "Success"));
        when(paymentService.getallpayment()).thenReturn(paymentList);

        List<Payment> result = paymentController.getAllPayments();

        assertEquals(paymentList, result);
    }
}
