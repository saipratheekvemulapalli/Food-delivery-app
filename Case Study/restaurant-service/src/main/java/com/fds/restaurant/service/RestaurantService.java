package com.fds.restaurant.service;

import java.util.List;

import com.fds.restaurant.model.Restaurant;

public interface RestaurantService {
	
	Restaurant addRestaurant(Restaurant restaurant);
	List<Restaurant> getAllRestaurants();
	Restaurant getRestaurantById(String restaurantId);
	String deleteRestaurant(String restaurantId);
	List<Restaurant> findByLocation(String location);
	List<Restaurant> findByRestaurantName(String restaurantName);
	Restaurant updateRestaurant(String restaurantId,Restaurant restaurant);
	String giveTheRating(String restaurnatId,int rating);
}
