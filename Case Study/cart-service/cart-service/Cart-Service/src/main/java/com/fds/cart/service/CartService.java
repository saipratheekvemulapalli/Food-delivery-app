package com.fds.cart.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fds.cart.entity.Cart;
import com.fds.cart.exception.CartNotFoundException;

@Service
public interface CartService {
	
	public Cart addCart(Cart cart);
	
	public Cart getCartById(int cartId);
	
	public List<Cart> getallcarts();

	public Cart updateCart(int cartId, Cart cart);
	
	public String deleteCartById(int cartId);

	public Cart addItemToCart(int cartId,int itemId);

	Cart deleteCartItem(int cartId, int itemId) throws CartNotFoundException;

	Cart decreaseItem(int itemId, int cartId) throws CartNotFoundException;
	
	public double cartTotal(Cart cart);

}
