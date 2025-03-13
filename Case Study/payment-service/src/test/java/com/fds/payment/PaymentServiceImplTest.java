package com.fds.payment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.fds.payment.exception.PaymentException;
import com.fds.payment.external.Order;
import com.fds.payment.external.OrderProxy;
import com.fds.payment.model.Payment;
import com.fds.payment.repository.PaymentRepository;
import com.fds.payment.service.PaymentServiceImpl;

@SpringBootTest
public class PaymentServiceImplTest {

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private OrderProxy orderProxy;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDoPayment() {
        Payment payment = new Payment(1l, 1,new Date(), "User123", 100.0, "Payment Successfull");
        when(paymentRepository.save(any())).thenReturn(payment);
        Order o = new Order();
        Payment result = paymentService.doPayment(o);

        assertNotNull(result);
        assertEquals(1, result.getTransactionId());
        assertEquals("Payment Successfull", result.getTransactionStatus());
    }

    @Test
    public void testDoPaymentFailure() {
        when(paymentRepository.save(any())).thenThrow(new PaymentException("Payment Failed of RS 100.0"));
        Order o = new Order();
        assertThrows(PaymentException.class, () -> paymentService.doPayment(o)
        );
    }

    @Test
    public void testGetPayment() {
        Payment payment = new Payment(1,1,new Date(), "User123", 100.0, "Payment Successfull");
        when(paymentRepository.findByOrderId(1)).thenReturn(payment);

        Payment result = paymentService.getPayment(1);

        assertNotNull(result);
       
    }}
