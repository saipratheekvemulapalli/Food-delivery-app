package com.fds.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fds.order.controller.OrderController;
import com.fds.order.model.Order;
import com.fds.order.service.OrderService;

public class OrderControllerTest {


	@Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @SuppressWarnings("deprecation")
	@BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testMakeAOrder() {
        Order order = new Order();
        int cartId = 1;
        Mockito.when(orderService.makeOrder(order, cartId)).thenReturn("Order Created");
        ResponseEntity<String> response = orderController.makeAOrder(order, cartId);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Order Created", response.getBody());
    }

    @Test
    void testCancelOrder() {
        int orderId = 1;
        Mockito.when(orderService.cancelOrder(orderId)).thenReturn("Order Canceled");
        ResponseEntity<String> response = orderController.cancelOrder(orderId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Order Canceled", response.getBody());
    }

    @Test
    void testUpdateAnOrder() {
        int orderId = 1;
        Order order = new Order();
        Mockito.when(orderService.updateOrder(orderId)).thenReturn(order);
        ResponseEntity<Order> response = orderController.updateAnOrder(orderId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testViewAnOrder() {
        int orderId = 1;
        Order order = new Order();
        Mockito.when(orderService.getOrder(orderId)).thenReturn(order);
        ResponseEntity<Order> response = orderController.viewAnOrder(orderId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testViewByOrderName() {
        String username = "testuser";
        List<Order> orders = new ArrayList<>();
        Mockito.when(orderService.getOrderByUsername(username)).thenReturn(orders);
        ResponseEntity<List<Order>> response = orderController.viewByOrderName(username);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(orders, response.getBody());
    }

    @Test
    void testViewAllOrder() {
        List<Order> orders = new ArrayList<>();
        Mockito.when(orderService.getAllOrders()).thenReturn(orders);
        ResponseEntity<List<Order>> response = orderController.viewAllOrder();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(orders, response.getBody());
    }
}