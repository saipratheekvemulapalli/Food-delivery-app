package com.fds.cart.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fds.cart.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {
	public Cart findById(int cartId);
	//Optional<Cart> findById(int cartId);

}
