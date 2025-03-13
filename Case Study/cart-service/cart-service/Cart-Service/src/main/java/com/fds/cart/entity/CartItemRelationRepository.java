package com.fds.cart.entity;



import com.fds.cart.entity.CartItemRelation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRelationRepository extends JpaRepository<CartItemRelation, Integer> {
}
