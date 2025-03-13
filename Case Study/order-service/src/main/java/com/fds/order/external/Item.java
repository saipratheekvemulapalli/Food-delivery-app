package com.fds.order.external;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
	private String itemId;
	private String itemName;
	private String category;
//	private String type;
	private String description;
	private double price;

}
