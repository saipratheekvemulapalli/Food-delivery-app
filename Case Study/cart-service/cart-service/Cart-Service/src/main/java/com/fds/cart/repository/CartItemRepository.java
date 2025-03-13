package com.fds.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fds.cart.entity.cartItem;

@Repository
public interface CartItemRepository extends JpaRepository<cartItem, Integer> {

}
