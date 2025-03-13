package com.fds.restaurant.service;

import java.util.List;

import com.fds.restaurant.model.Item;

public interface ItemService {
	
	Item addItem(Item item);
	Item viewItemById(String itemId);
	List<Item> viewAllItems();
	List<Item>  viewItemByName(String name);
	String deleteItemById(String id);
	Item updateItem(String itemId, Item item);
	List<Item> viewByRestaurantName(String name);
	
}
