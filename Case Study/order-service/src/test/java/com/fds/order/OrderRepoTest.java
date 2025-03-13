package com.fds.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.fds.order.model.Order;
import com.fds.order.repo.OrderRepo;
import com.fds.order.service.OrderServiceImpl;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class OrderRepoTest {

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private OrderRepo orderRepo;
    
   

    @Test
    public void testFindByUsername() {
        // Create a sample order
        Order order = new Order();
        order.setOrderId(1);
        order.setUsername("testuser");
        order.setOrderName("Test Order");
        order.setOrderStatus("Pending");
        
        //when(orderRepo.findByUsername("testuser")).thenReturn(null);
        

        // Mock the behavior of the orderRepo
        when(orderRepo.findByUsername("testuser")).thenReturn(List.of(order));

        // Call the service method
        List<Order> orders = orderService.getOrderByUsername("testuser");

        // Verify that the orderRepo method was called
        verify(orderRepo, Mockito.times(1)).findByUsername("testuser");

        // Check the results
        assertThat(orders).hasSize(1);
        Order resultOrder = orders.get(0);
        assertThat(resultOrder.getUsername()).isEqualTo("testuser");
        assertThat(resultOrder.getOrderName()).isEqualTo("Test Order");
        assertThat(resultOrder.getOrderStatus()).isEqualTo("Pending");
    }
    

}

