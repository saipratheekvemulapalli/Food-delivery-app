package com.fds.externalServices;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fds.externalClass.Cart;

@FeignClient(name="cart-service",url = "http://localhost:9993/cart")
public interface CartProxy {
	@GetMapping("/getallcarts")
	public ResponseEntity<List<Cart>> getAllCarts() ;
	
	@PostMapping("/addingitemtocart/{cartId}/{itemId}")
	public ResponseEntity<Cart> addItemToCart(@PathVariable int cartId, @PathVariable int itemId);

	@GetMapping("/{cartId}")
	public ResponseEntity<Cart> getCartById(@PathVariable int cartId) ;

	@PutMapping("/deleteItem/{cartId}/{itemId}")
	public ResponseEntity<Cart> deleteCartItem(@PathVariable int cartId, @PathVariable int itemId) ;

	@PutMapping("/decreaseQuant/{itemId}/{cartId}")
	public Cart decreaseItem(@PathVariable int itemId, @PathVariable int cartId) ;

	@DeleteMapping("/deleteCart/{cartId}")
	public String deleteCart(@PathVariable int cartId) ;
	
	@PostMapping("/addCart")
	public Cart addCart(@RequestBody Cart cart) ;
}
