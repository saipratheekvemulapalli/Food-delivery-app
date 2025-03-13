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

import com.fds.externalClass.Item;
import com.fds.externalClass.Restaurant;

import jakarta.validation.Valid;

@FeignClient(name="restaurant-service",url = "http://localhost:9992/restaurant")
public interface RestaurantProxy {
	
	@PostMapping("/addRestaurant")
	public ResponseEntity<Restaurant> addRestaurant(@RequestBody @Valid Restaurant restaurant);
	
	@GetMapping("/viewAllRestaurants")
	public ResponseEntity<List<Restaurant>> getAllRestaurants();
	
	@GetMapping("/viewRestaurantById/{restaurantId}")
	public ResponseEntity<Restaurant> getRestaurantById(@PathVariable String restaurantId);
	
	@DeleteMapping("/deleteRestaurant/{restaurantId}")
	public ResponseEntity<String> deleteRestaurantById(@PathVariable String restaurantId);
	
	@GetMapping("/viewRestaurantsByLocation/{location}")
	public ResponseEntity<List<Restaurant>> getRestaurantByLocation(@PathVariable String location);
	
	@GetMapping("/viewRestaurantsByName/{restaurantName}")
	public ResponseEntity<List<Restaurant>> getRestaurantByName(@PathVariable String restaurantName);
	
	@PutMapping("/updateRestaurant/{restaurantId}")
	public ResponseEntity<Restaurant> updateRestaurant(@PathVariable String restaurantId, @RequestBody Restaurant restaurant);
		
	@PutMapping("/giveRating/{restaurantId}/{rating}")
	public ResponseEntity<String> giveRating(@PathVariable String restaurantId, @PathVariable int rating);
	
	//item
	
	@PostMapping("item/add")
	public ResponseEntity<Item> addItem(@RequestBody @Valid Item item);
	
	@GetMapping("item/viewAll")
	public ResponseEntity<List<Item>> getAllItems();
	
	@GetMapping("item/viewById/{itemId}")
	public ResponseEntity<Item> getItemById(@PathVariable String itemId);
	
	@DeleteMapping("item/delete/{itemId}")
	public ResponseEntity<String> deleteItemById(@PathVariable String itemId);
	
	@GetMapping("item/viewByName/{itemName}")
	public ResponseEntity<List<Item>> getItemByName(@PathVariable String itemName);
	
	@PutMapping("item/update/{itemId}")
	public ResponseEntity<Item> updateItem(@PathVariable String itemId, @RequestBody Item item);
	
	@GetMapping("/item/getItemsByrestaurantName/{restaurantName}")
	public ResponseEntity<List<Item>> getItemsByrestaurantName(@PathVariable String restaurantName);
}
