package com.fds.cart.entity;




import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor 
public class Item {
	
	@Id
	private String itemId;
	private String restaurantId;
	private String itemName;
	private String category;
	private String description;
	private double price;
	private String image;

}