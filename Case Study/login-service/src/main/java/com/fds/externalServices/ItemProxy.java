//package com.fds.externalServices;
//
//import java.util.List;
//
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//import com.fds.externalClass.Item;
//
//import jakarta.validation.Valid;
//
////@FeignClient(name="restaurant-service",url = "http://localhost:9992/restaurant/item")
//public interface ItemProxy {
//	@PostMapping("/add")
//	public ResponseEntity<Item> addItem(@RequestBody @Valid Item item);
//	
//	@GetMapping("/viewAll")
//	public ResponseEntity<List<Item>> getAllItems();
//	
//	@GetMapping("/viewById/{itemId}")
//	public ResponseEntity<Item> getItemById(@PathVariable String itemId);
//	
//	@DeleteMapping("/delete/{itemId}")
//	public ResponseEntity<String> deleteItemById(@PathVariable String itemId);
//	
//	@GetMapping("/viewByName/{itemName}")
//	public ResponseEntity<List<Item>> getItemByName(@PathVariable String itemName);
//	
//	@PutMapping("/update/{itemId}")
//	public ResponseEntity<Item> updateItem(@PathVariable String itemId, @RequestBody Item item);
//}
