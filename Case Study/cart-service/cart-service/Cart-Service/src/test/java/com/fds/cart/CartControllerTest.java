package com.fds.cart;
import com.fds.cart.controller.CartController;
import com.fds.cart.entity.Cart;
import com.fds.cart.exception.CartNotFoundException;
import com.fds.cart.service.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class CartControllerTest {

    @InjectMocks
    private CartController cartController;

    @Mock
    private CartService cartService;

    @Mock
    private RestTemplate restTemplate;

    @SuppressWarnings("deprecation")
	@BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddCart() {
        Cart cart = new Cart();
        when(cartService.addCart(cart)).thenReturn(cart);

        ResponseEntity<Cart> response = cartController.addCart(cart);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cart, response.getBody());
    }

    @Test
    public void testGetAllCarts() {
        List<Cart> carts = new ArrayList<>();
        when(cartService.getallcarts()).thenReturn(carts);

        ResponseEntity<List<Cart>> response = cartController.getAllCarts();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(carts, response.getBody());
    }

    @Test
    public void testAddItemToCart() {
        int cartId = 1;
        int itemId = 2;

        when(cartService.addItemToCart(cartId, itemId)).thenReturn(new Cart());

        ResponseEntity<Cart> response = cartController.addItemToCart(cartId, itemId);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testGetCartById() {
        int cartId = 1;
        Cart cart = new Cart();
        when(cartService.getCartById(cartId)).thenReturn(cart);

        ResponseEntity<Cart> response = cartController.getCartById(cartId);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(cart, response.getBody());
    }

    @Test
    public void testDeleteCartItem() {
        int cartId = 1;
        int itemId = 2;

        when(cartService.deleteCartItem(cartId, itemId)).thenReturn(new Cart());

        ResponseEntity<Cart> response = cartController.deleteCartItem(cartId, itemId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testDecreaseItem() {
        int itemId = 1;
        int cartId = 2;
        Cart cart = new Cart();

        when(cartService.decreaseItem(itemId, cartId)).thenReturn(cart);

        Cart response = cartController.decreaseItem(itemId, cartId);

        assertNotNull(response);
    }


    @Test
    public void testDeleteCartCartNotFoundException() {
        int cartId = 1;

        when(cartService.deleteCartById(cartId)).thenThrow(new CartNotFoundException("Cart not found"));

        assertThrows(RuntimeException.class, () -> cartController.deleteCart(cartId));
    }
    
    
}
