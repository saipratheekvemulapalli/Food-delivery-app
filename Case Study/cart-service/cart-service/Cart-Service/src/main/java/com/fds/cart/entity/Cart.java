package com.fds.cart.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cart {

    @Id
    private Integer cartId; // Assuming this is an Integer, make sure it matches your database type

    private double totalPrice;

    @OneToMany(cascade = CascadeType.ALL) 
    @JoinColumn(name = "cart_cart_id") 
    private List<cartItem> items;

}
