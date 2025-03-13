package com.fds.cart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fds.cart.entity.cartItem;
import com.fds.cart.service.CartItemsImpl;

@RestController
@RequestMapping("/cartitems")
public class CartItemController {
	
	@Autowired
	CartItemsImpl cartItemsImpl;
	
	@PostMapping("/addingitemincart")
	public ResponseEntity<cartItem> addCartItem(@RequestBody cartItem cartitem1){
		return cartItemsImpl.addCartItem(cartitem1);
		
	}
	
}
