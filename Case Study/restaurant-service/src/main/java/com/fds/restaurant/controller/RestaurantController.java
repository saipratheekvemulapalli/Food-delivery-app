package com.fds.restaurant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fds.restaurant.model.Restaurant;
import com.fds.restaurant.service.RestaurantService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
	
	@Autowired
	RestaurantService restaurantService;
	
	@PostMapping("/addRestaurant")
	public ResponseEntity<Restaurant> addRestaurant(@RequestBody @Valid Restaurant restaurant){
		log.info("Inside the addRestaurant method of Controller");
		log.info("Adding the restaurant");
		return ResponseEntity.ok(restaurantService.addRestaurant(restaurant));
	}
	
	@GetMapping("/viewAllRestaurants")
	public ResponseEntity<List<Restaurant>> getAllRestaurants(){
		log.info("Inside the getAllRestaurants method of Controller");
		log.info("Retrieving All Restaurants");
		return ResponseEntity.ok(restaurantService.getAllRestaurants());
	}
	
	@GetMapping("/viewRestaurantById/{restaurantId}")
	public ResponseEntity<Restaurant> getRestaurantById(@PathVariable String restaurantId){
		log.info("Inside the getRestaurantById method of Controller");
		log.info("Retrieving the Restaurant with "+restaurantId+" id");
		return ResponseEntity.ok(restaurantService.getRestaurantById(restaurantId));
	}
	
	@DeleteMapping("/deleteRestaurant/{restaurantId}")
	public ResponseEntity<String> deleteRestaurantById(@PathVariable String restaurantId){
		log.info("Inside the deleteRestaurantById method of Controller");
		log.info("Deleting the Restaurant with "+restaurantId+" id");
		return ResponseEntity.ok(restaurantService.deleteRestaurant(restaurantId));
	}
	
	@GetMapping("/viewRestaurantsByLocation/{location}")
	public ResponseEntity<List<Restaurant>> getRestaurantByLocation(@PathVariable String location){
		log.info("Inside the getRestaurantByLocation method of Controller");
		log.info("Retrieving All Restaurants based on Location");
		return ResponseEntity.ok(restaurantService.findByLocation(location));
	}
	
	@GetMapping("/viewRestaurantsByName/{restaurantName}")
	public ResponseEntity<List<Restaurant>> getRestaurantByName(@PathVariable String restaurantName){
		log.info("Inside the getRestaurantByName method of Controller");
		log.info("Retrieving All Restaurants based on Name");
		return ResponseEntity.ok(restaurantService.findByRestaurantName(restaurantName));
	}
	
	@PutMapping("/updateRestaurant/{restaurantId}")
	public ResponseEntity<Restaurant> updateRestaurant(@PathVariable String restaurantId, @RequestBody Restaurant restaurant){
		log.info("Inside the updateRestaurant method of Controller");
		log.info("Updating the Restaurant with "+restaurantId+" id");
		return ResponseEntity.ok(restaurantService.updateRestaurant(restaurantId,restaurant));
	}
	
	@PutMapping("/giveRating/{restaurantId}/{rating}")
	public ResponseEntity<String> giveRating(@PathVariable String restaurantId, @PathVariable int rating){
		log.info("Inside the giveRating method of Controller");
		log.info("Rating the Restaurant with "+restaurantId+" id ");
		return ResponseEntity.ok(restaurantService.giveTheRating(restaurantId,rating));
	}
	
	
	
	

	

}
