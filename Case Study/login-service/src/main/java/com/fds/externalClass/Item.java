package com.fds.externalClass;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
	
	private String itemId;
	private String restaurantId;
	private String itemName;
	private String category;
	private String description;
	private double price;
	private String image;

}
