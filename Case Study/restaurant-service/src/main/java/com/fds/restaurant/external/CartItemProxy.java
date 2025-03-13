package com.fds.restaurant.external;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name="cart-service",url = "http://localhost:9993/cartitems")
public interface CartItemProxy {

	@PostMapping("/addingitemincart")
	public ResponseEntity<cartItem> addCartItem(@RequestBody cartItem cartitem1);
}
