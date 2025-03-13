package com.fds.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.fds.order.exception.OrderException;
import com.fds.order.model.Order;
import com.fds.order.repo.OrderRepo;
import com.fds.order.service.OrderServiceImpl;

@SpringBootTest
public class OrderServiceImplTest {

    @Mock
    private OrderRepo orderRepo;
    
    @Mock
    private RestTemplate restTemplate;


    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach
    public void setup() {
        // Initialize your mocks or perform setup here
    }

    @Test
    public void testUpdateOrder() {
        Order order = new Order();
        order.setOrderId(1234);
        order.setOrderStatus("Successful");
        // Mock the behavior of dependencies
        when(orderRepo.findById(1234)).thenReturn(Optional.of(order));
        when(orderRepo.save(Mockito.any())).thenReturn(order);

        Order updatedOrder = orderService.updateOrder(1234);

        assertNotNull(updatedOrder);
        assertEquals("Successful", updatedOrder.getOrderStatus());
    }

    @Test
    public void testUpdateOrderOrderNotFound() {
        when(orderRepo.findById(1234)).thenReturn(Optional.empty());

        assertThrows(OrderException.class, () -> {
            orderService.updateOrder(1234);
        });
    }

    @Test
    public void testCancelOrder() {
        when(orderRepo.findById(1234)).thenReturn(Optional.of(new Order()));

        String result = orderService.cancelOrder(1234);

        assertEquals("Order is deleted Successfully", result);
    }

    @Test
    public void testCancelOrderOrderNotFound() {
        when(orderRepo.findById(1234)).thenReturn(Optional.empty());

        assertThrows(OrderException.class, () -> {
            orderService.cancelOrder(1234);
        });
    }

    @Test
    public void testGetOrder() {
        Order order = new Order();
        order.setOrderId(1234);
        when(orderRepo.findById(1234)).thenReturn(Optional.of(order));

        Order result = orderService.getOrder(1234);

        assertNotNull(result);
        assertEquals(1234, result.getOrderId());
    }

    @Test
    public void testGetOrderOrderNotFound() {
        when(orderRepo.findById(1234)).thenReturn(Optional.empty());

        assertThrows(OrderException.class, () -> {
            orderService.getOrder(1234);
        });
    }

    @Test
    public void testGetAllOrders() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order());
        when(orderRepo.findAll()).thenReturn(orders);

        List<Order> result = orderService.getAllOrders();

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    public void testGetAllOrdersNoOrdersFound() {
        when(orderRepo.findAll()).thenReturn(new ArrayList<>());

        assertThrows(OrderException.class, () -> {
            orderService.getAllOrders();
        });
    }

    @Test
    public void testGetOrderByUsername() {
        List<Order> orders = new ArrayList<>();
        Order order = new Order();
        order.setUsername("testuser");
        orders.add(order);
        when(orderRepo.findByUsername("testuser")).thenReturn(orders);

        List<Order> result = orderService.getOrderByUsername("testuser");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("testuser", result.get(0).getUsername());
    }

    @Test
    public void testGetOrderByUsernameNoOrdersFound() {
        when(orderRepo.findByUsername("testuser")).thenReturn(new ArrayList<>());

        assertThrows(OrderException.class, () -> {
            orderService.getOrderByUsername("testuser");
        });
    }
    

 
}
