package com.fds.externalClass;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {
	private String restaurantId;
	private String restaurantName;
	private int rating;
	private String type;
	private String location;

}