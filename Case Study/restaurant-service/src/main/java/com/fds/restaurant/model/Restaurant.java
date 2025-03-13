package com.fds.restaurant.model;




import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Restaurant {
	@Id
	private String restaurantId;
	private String restaurantName;
	private int rating;
	private String type;
	private String location;
//	private List<Item> items;

}
