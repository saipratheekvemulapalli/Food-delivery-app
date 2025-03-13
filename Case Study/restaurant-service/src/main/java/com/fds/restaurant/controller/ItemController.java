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

import com.fds.restaurant.model.Item;
import com.fds.restaurant.service.ItemService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;


/**
 * This class handles user-related operations.
 * 
 */
/**
 * @author svemulap
 *
 */
/**
 * @author svemulap
 *
 */
@Slf4j
@RestController
@RequestMapping("/restaurant/item")
public class ItemController {
	@Autowired
	ItemService itemService;
	
	@PostMapping("/add")
	public ResponseEntity<Item> addItem(@RequestBody @Valid Item item){
		log.info("Inside the addItem method of Controller");
		log.info("Adding the item");
		return ResponseEntity.ok(itemService.addItem(item));
	}
	
	@GetMapping("/viewAll")
	public ResponseEntity<List<Item>> getAllItems(){
		log.info("Inside the getAllItems method of Controller");
		log.info("Retrieving All Items");
		return ResponseEntity.ok(itemService.viewAllItems());
		
	}
	
	@GetMapping("/viewById/{itemId}")
	public ResponseEntity<Item> getItemById(@PathVariable String itemId){
		log.info("Inside the getItemById method of Controller");
		log.info("Retrieving the Item with "+itemId+" id");
		return ResponseEntity.ok(itemService.viewItemById(itemId));
	}
	
	@DeleteMapping("/delete/{itemId}")
	public ResponseEntity<String> deleteItemById(@PathVariable String itemId){
		log.info("Inside the deleteItemById method of Controller");
		log.info("Deleting the Item with "+itemId+" id");
		return ResponseEntity.ok(itemService.deleteItemById(itemId));
	}
	
//	@GetMapping("/viewByLocation/{location}")
//	public ResponseEntity<List<Item>> getRestaurantByLocation(@PathVariable String location){
//		log.info("Inside the getRestaurantByLocation method of Controller");
//		log.info("Retrieving All Items based on Location");
//		return ResponseEntity.ok(itemService.findByLocation(location));
//	}
	
	@GetMapping("/viewByName/{itemName}")
	public ResponseEntity<List<Item>> getItemByName(@PathVariable String itemName){
		log.info("Inside the getItemByName method of Controller");
		log.info("Retrieving All Items based on Name");
		return ResponseEntity.ok(itemService.viewItemByName(itemName));
	}
	
	@PutMapping("/update/{itemId}")
	public ResponseEntity<Item> updateItem(@PathVariable String itemId, @RequestBody Item item){
		log.info("Inside the updateItem method of Controller");
		log.info("Updating the Item with "+itemId+" id");
		return ResponseEntity.ok(itemService.updateItem(itemId,item));
	}
	
	@GetMapping("/getItemsByrestaurantName/{restaurantName}")
	public ResponseEntity<List<Item>> getItemsByrestaurantName(@PathVariable String restaurantName){
		log.info("Inside the getItemsByrestaurantName method of Controller");
		log.info("Retrieving All Items based on Restaurant Name");
		return ResponseEntity.ok(itemService.viewByRestaurantName(restaurantName));
	}
	
	
	
	
	

	

}
