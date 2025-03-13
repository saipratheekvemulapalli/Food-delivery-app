package com.fds.restaurant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fds.restaurant.model.Restaurant;

public interface RestaurantRepo extends JpaRepository<Restaurant, String> {
	
	List<Restaurant> findByRestaurantName(String restaurantName);
	List<Restaurant> findByType(String type);
	List<Restaurant> findByLocation(String location);
//	List<Item> findByItems (String item);
	
}
