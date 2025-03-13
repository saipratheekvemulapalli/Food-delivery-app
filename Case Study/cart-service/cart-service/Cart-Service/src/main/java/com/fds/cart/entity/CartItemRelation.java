package com.fds.cart.entity;



import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "cart_item_relation")
public class CartItemRelation {

    @EmbeddedId
    private CartItemRelationId id; // Use composite key

    // Constructors, getters, and setters
    public CartItemRelation() {}

    public CartItemRelation(Integer cartId, Integer itemId) {
        this.id = new CartItemRelationId(cartId, itemId);
    }

    public Integer getCartId() {
        return id.cartId;
    }

    public Integer getItemId() {
        return id.itemId;
    }
}
