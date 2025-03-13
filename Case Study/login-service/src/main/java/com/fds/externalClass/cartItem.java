package com.fds.externalClass;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
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
	@NotEmpty
	@Min(0)
	private double price;
	@Min(1)
	private int quantity;
	private String image;

}
