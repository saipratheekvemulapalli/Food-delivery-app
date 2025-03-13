package com.fds.cart.entity;


import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Embeddable;

@Embeddable
public class CartItemRelationId implements Serializable {
    public Integer cartId;  // Make this public
    public Integer itemId;  // Make this public

    // Default constructor
    public CartItemRelationId() {}

    // Parameterized constructor
    public CartItemRelationId(Integer cartId, Integer itemId) {
        this.cartId = cartId;
        this.itemId = itemId;
    }

    // Getters (optional if you only need access through the fields)
    public Integer getCartId() {
        return cartId;
    }

    public Integer getItemId() {
        return itemId;
    }

    // Equals and hashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartItemRelationId)) return false;
        CartItemRelationId that = (CartItemRelationId) o;
        return Objects.equals(cartId, that.cartId) && Objects.equals(itemId, that.itemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartId, itemId);
    }
}
