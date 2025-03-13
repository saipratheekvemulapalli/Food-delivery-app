package com.fds.cart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fds.cart.entity.Cart;
import com.fds.cart.exception.CartNotFoundException;
import com.fds.cart.repository.CartRepository;
import com.fds.cart.service.CartService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/cart")

public class CartController {
	@Autowired
	CartService cartService;
	
	
	
	@Autowired
	RestTemplate restTemplate;
	
	
	@PostMapping("/addCart")
	public ResponseEntity<Cart> addCart(@RequestBody Cart cart) {
		log.info("Inside the addCart method of Controller");
		log.info("creating a Cart");
		return ResponseEntity.ok(cartService.addCart(cart));
	}


	@GetMapping("/getallcarts")
	public ResponseEntity<List<Cart>> getAllCarts() {
		log.info("Inside the getAllCarts method of Controller");
		log.info("Retrieving the AllCarts");
		return ResponseEntity.ok(cartService.getallcarts());
	}

	@PostMapping("/addingitemtocart/{cartId}/{itemId}")
	public ResponseEntity<Cart> addItemToCart(@PathVariable int cartId, @PathVariable int itemId)
	{ 
		log.info("Inside the addItemToCart method of Controller");
	    log.info("Adding the item to cart");

	
		Cart updatedCart = cartService.addItemToCart(cartId, itemId);
		return new ResponseEntity<>(updatedCart, HttpStatus.CREATED);
	}

	@GetMapping("/{cartId}")
	public ResponseEntity<Cart> getCartById(@PathVariable int cartId) {
		log.info("Inside the getCartById method of Controller");
	    log.info("Retrieving the cart by Id");
		return new ResponseEntity<>(cartService.getCartById(cartId), HttpStatus.CREATED);
	}

	@PutMapping("/deleteItem/{cartId}/{itemId}")
	public ResponseEntity<Cart> deleteCartItem(@PathVariable int cartId, @PathVariable int itemId) {
		log.info("Inside the deleteCartItem method of Controller");
	    log.info("Deleting the cart item");
		// Call the service to delete the item
		Cart updatedCart = cartService.deleteCartItem(cartId, itemId);
		return new ResponseEntity<>(updatedCart, HttpStatus.OK);
	}

	@PutMapping("/decreaseQuant/{itemId}/{cartId}")
	public Cart decreaseItem(@PathVariable int itemId, @PathVariable int cartId) {
		log.info("Inside the decreaseItem method of Controller");
	    log.info("Decreasing the item in the cart");
		try {
			return cartService.decreaseItem(itemId, cartId);
		} catch (CartNotFoundException e) {
			// Handle CartNotFoundException appropriately, e.g., return a specific error
			// response
			// For simplicity, rethrowing it here
			throw new RuntimeException("Cart not found with ID: " + cartId, e);
		}
	}

	@DeleteMapping("/deleteCart/{cartId}")
	public String deleteCart(@PathVariable int cartId) {
		log.info("Inside the deleteCart method of Controller");
	    log.info("deleting the cart");
	    
	    if(cartRepository.existsById(cartId)) {
	    	cartService.deleteCartById(cartId);
	    	return "The cart with id "+cartId+" is removed";
	    }else {
	    	throw new CartNotFoundException("There is no cart with id "+cartId);
	    }
	    
	}

	public CartController(CartService cartService) {
		this.cartService = cartService;
	}

	public CartController() {

	}
	@Autowired
	CartRepository cartRepository;


}
