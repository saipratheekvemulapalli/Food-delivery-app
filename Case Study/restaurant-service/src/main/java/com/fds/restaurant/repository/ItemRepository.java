package com.fds.restaurant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.fds.restaurant.model.Item;


public interface ItemRepository extends JpaRepository<Item, String>{
	List<Item> findByItemName(String name);
	List<Item> findByRestaurantId(String restaurantId);
	
}
