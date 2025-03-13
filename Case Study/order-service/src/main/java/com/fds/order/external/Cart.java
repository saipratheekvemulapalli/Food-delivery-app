package com.fds.order.external;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart{
    private int cartId;

    private List<cartItem> items;

    private double totalPrice;

}