package com.fds.payment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.fds.payment.exception.PaymentException;
import com.fds.payment.external.Order;
import com.fds.payment.model.Payment;
import com.fds.payment.repository.PaymentRepository;
import com.fds.payment.service.PaymentServiceImpl;

@SpringBootTest
public class PaymentRepositoryTest {

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Mock
    private PaymentRepository paymentRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDoPaymentSuccess() {
        Payment payment = new Payment();
        payment.setTransactionId(1);
        payment.setUsername("testUser");
        payment.setOrderId(11);
        payment.setAmount(100.0);
        payment.setTransactionStatus("Payment Successfull");

        when(paymentRepository.save(any(Payment.class))).thenReturn(payment);
        Order o = new Order();

        Payment result = paymentService.doPayment(o);

        assertEquals(1, result.getTransactionId());
        assertEquals("testUser", result.getUsername());
        assertEquals(11, result.getOrderId());
        assertEquals(100.0, result.getAmount());
        assertEquals("Payment Successfull", result.getTransactionStatus());
    }

    @Test
    public void testDoPaymentFailure() {
        when(paymentRepository.save(any(Payment.class))).thenThrow(new PaymentException("Payment Failed of RS 100.0"));
        Order o = new Order();
        o.setCost(100);

        PaymentException exception = assertThrows(PaymentException.class,
            () -> paymentService.doPayment(o)
        );

        assertEquals("Payment Failed of RS 100.0", exception.getMessage());
    }

    @Test
    public void testGetPayment() {
        Payment payment = new Payment();
        payment.setTransactionId(1);
        payment.setUsername("testUser");
        payment.setOrderId(1);
        payment.setAmount(100.0);
        payment.setTransactionStatus("Payment Successfull");

        when(paymentRepository.findByOrderId(1)).thenReturn(payment);

        Payment result = paymentService.getPayment(1);

        assertEquals(1, result.getTransactionId());
        assertEquals("testUser", result.getUsername());
        assertEquals(1, result.getOrderId());
        assertEquals(100.0, result.getAmount());
        assertEquals("Payment Successfull", result.getTransactionStatus());
    }

    @Test
    public void testGetPaymentNotFound() {
        when(paymentRepository.findByOrderId(1)).thenReturn(null);

        PaymentException exception = assertThrows(PaymentException.class,
            () -> paymentService.getPayment(1)
        );

        assertEquals("Payment not found with transactionId 1", exception.getMessage());
    }
}

