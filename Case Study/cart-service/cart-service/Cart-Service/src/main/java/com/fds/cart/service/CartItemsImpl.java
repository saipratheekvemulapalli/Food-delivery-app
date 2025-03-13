package com.fds.cart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fds.cart.entity.cartItem;
import com.fds.cart.repository.CartItemRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CartItemsImpl {
	
	@Autowired
	CartItemRepository cartItemRepository;
	
	@Transactional
	public ResponseEntity<cartItem> addCartItem(cartItem cartitem1) {
	    log.info("Adding cart item: {}", cartitem1);
	    try {
	        cartItem savedItem = cartItemRepository.save(cartitem1);
	        log.info("Cart item saved successfully: {}", savedItem);
	        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
	    } catch (Exception e) {
	        log.error("Error saving cart item: {}", e.getMessage(), e);
	        throw new RuntimeException("Failed to save cart item", e);
	    }
	}


	
	
}
