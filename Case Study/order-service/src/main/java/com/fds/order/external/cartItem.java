package com.fds.order.external;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class cartItem {
	private int itemId;

	private String itemName;
	private String itemInfo;
	
	private double price;
	private int quantity;
	private String image;

}
