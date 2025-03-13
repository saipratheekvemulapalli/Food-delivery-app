package com.fds.restaurant.model;



import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Item {
	
	@Id
	@NotBlank(message ="itemId is required.")
	private String itemId;
	@NotBlank(message ="restaurantId is required.")
	private String restaurantId;
	@NotBlank(message ="itemName is required.")
	private String itemName;
	@NotBlank(message ="category is required.")
	private String category;
	@NotBlank(message ="description is required.")
	private String description;
	@NotNull(message ="price is required.")
	private double price;
	@NotBlank(message ="image is required.")
	private String image;

}
