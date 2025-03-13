package com.fds.order.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "restaurant-service", url="http://localhost:9992/restaurant")
public interface RestaurantProoxy {
	
	@GetMapping("/viewRestaurantById/{restaurantId}")
	public Restaurant getRestaurantById(@PathVariable String restaurantId);

	
}
