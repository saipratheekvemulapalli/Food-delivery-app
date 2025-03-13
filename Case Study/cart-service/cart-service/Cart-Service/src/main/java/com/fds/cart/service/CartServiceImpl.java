package com.fds.cart.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fds.cart.entity.Cart;
import com.fds.cart.entity.CartItemRelation;
import com.fds.cart.entity.CartItemRelationRepository;
import com.fds.cart.entity.Item;
import com.fds.cart.entity.cartItem;
import com.fds.cart.exception.CartAlreadyExistsException;
import com.fds.cart.exception.CartNotFoundException;
import com.fds.cart.repository.CartRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CartServiceImpl implements CartService {
	@Autowired
	CartRepository cartRepository;
	@Autowired
	RestTemplate restTemplate;
	
	//private static boolean b=true;
	
	@Autowired
	CartItemRelationRepository cartItemRelationRepository;
	
	@Override
	public Cart addCart(Cart cart) throws CartAlreadyExistsException {
	    log.info("addCart method is started");
	    log.info("Received cart: {}", cart);

	    if (cartRepository.existsById(cart.getCartId())) {
	        throw new CartAlreadyExistsException();
	    } else {
	        List<cartItem> items = new ArrayList<>();
	        List<CartItemRelation> cartItemRelations = new ArrayList<>();

	        for (cartItem item : cart.getItems()) {
	            String itemServiceUrl = "http://localhost:9992/restaurant/item/viewById/" + item.getItemId();
	            log.info("Fetching item from URL: {}", itemServiceUrl);
	            cartItem fetchedItem = restTemplate.getForObject(itemServiceUrl, cartItem.class);

	            if (fetchedItem == null) {
	                throw new RuntimeException("Unable to find item with ID: " + item.getItemId());
	            }

	            cartItem newCartItem = new cartItem();
	            newCartItem.setItemId(fetchedItem.getItemId());
	            newCartItem.setItemName(fetchedItem.getItemName());
	            newCartItem.setItemInfo(fetchedItem.getItemInfo());
	            newCartItem.setPrice(fetchedItem.getPrice());
	            newCartItem.setImage(fetchedItem.getImage());
	            newCartItem.setQuantity(item.getQuantity());

	            items.add(newCartItem);

	            // Add relationship to the list
	            cartItemRelations.add(new CartItemRelation(cart.getCartId(), newCartItem.getItemId()));
	        }

	        cart.setItems(items);
	        log.info("Items fetched and set: {}", items);

	        double totalPrice = items.stream()
	                .mapToDouble(item -> item.getPrice() * item.getQuantity())
	                .sum();
	        cart.setTotalPrice(totalPrice);

	        log.info("Calculated total price: {}", totalPrice);
	        Cart savedCart = cartRepository.save(cart);

	        // Save the relationships to the new table
	        cartItemRelationRepository.saveAll(cartItemRelations);

	        return savedCart;
	    }
	}







	@Override
	public Cart getCartById(int cartId) throws CartNotFoundException {
		log.info("getCartById method is started");
		if (cartRepository.existsById(cartId)) {
			log.info("Cart details of id exists " + cartId);
			return cartRepository.findById(cartId);
		} else {
			log.warn("Cart not found with id " + cartId);
			throw new CartNotFoundException("cart with id " + cartId+ " is not found");
		}
	}
	
	@Override
	public List<Cart> getallcarts() throws CartNotFoundException {
		log.info("getallcarts method is started");
		List<Cart> carts = cartRepository.findAll();
		if (carts.isEmpty()) {
			throw new CartNotFoundException("No cart exists");
		} else {
			return carts;
		}
	}

	@Override
	public Cart updateCart(int cartId, Cart cart) throws CartNotFoundException {
		log.info("updateCart method is started");
		if (cartRepository.existsById(cartId)) {
			Cart updatedCart = cartRepository.findById(cartId);
			updatedCart.setCartId(cart.getCartId());
			updatedCart.setItems(cart.getItems());
			updatedCart.setTotalPrice(cart.getTotalPrice());
			cartRepository.save(updatedCart);
			return updatedCart;

		} else {
			log.warn("No cart is found with id  " + cartId);
			throw new CartNotFoundException("No cart is found with id" + cartId);
		}

	}
	
	@Override
	public String deleteCartById(int cartId) {
		log.info("deleteCartById method is started");
		cartRepository.deleteById(cartId);
		return "Deleted Successfully";

	}


	@Override
	public double cartTotal(Cart cart) {
		log.info("cartTotal method is started");

		return cart.getTotalPrice();
	}


	@Override
	public Cart addItemToCart(int cartId,int itemId) {
		log.info("additemToCart method is started");
		// Fetch the item details from another service using RestTemplate
		Item item = restTemplate.getForObject("http://localhost:9992/restaurant/item/viewById/" + itemId, Item.class);
		log.info("Fetched item details: " + item);
		Cart cart ;
//		if(b) {
//		cart1 = new Cart();
//		addCart(cart1);
//		b=false;
//		}
		// Checking if the cart exists
		//Cart cart=cart1;
		if (cartRepository.existsById(cartId)) {
			cart = cartRepository.findById(cartId);

			// Checking if the item is already in the cart
			boolean itemExistsInCart = cart.getItems().stream().anyMatch(i -> i.getItemId() == itemId);
			
//			boolean itemExistsInCart=false;
//			for(cartItem ci: cart.getItems()) {
//				if(ci.getItemId()==itemId) {
//				itemExistsInCart=true;
//				break;
//				}
//			}
			if (itemExistsInCart) {
				// If the item exists in the cart, increase its quantity
				for (cartItem i : cart.getItems()) {
					if (i.getItemId() == itemId) {
						log.info("item already exists in the cart. Increasing quantity.");
						i.setQuantity(i.getQuantity() + 1);
						break;
					}
				}
			} else {
				// If the item is not in the cart, add it as a new item
				log.info("item not in the cart. Adding as a new item.");
				cartItem newItem = new cartItem();
				newItem.setItemId(itemId);
				newItem.setPrice(item.getPrice());
				newItem.setItemName(item.getItemName());
				newItem.setItemInfo(item.getDescription());
				newItem.setQuantity(1);
				newItem.setImage(item.getImage());
				cart.getItems().add(newItem);
			}
		} else {
			// If the cart doesn't exist, create a new cart and add the item as an item
			log.info("Cart doesn't exist. Creating a new cart and adding the item.");
			cart = new Cart();
			
			cart.setCartId(cartId);

			cartItem newItem = new cartItem();
			newItem.setItemId(itemId);
			newItem.setPrice(item.getPrice());
			newItem.setItemName(item.getItemName());
			newItem.setItemInfo(item.getDescription());
			newItem.setQuantity(1);
			newItem.setImage(item.getImage());

			List<cartItem> itemList = new ArrayList<>();
			itemList.add(newItem);
			cart.setItems(itemList);
		}

		// Calculate total price of the cart
		double totalPrice = calculateTotalPrice(cart);
		cart.setTotalPrice(totalPrice);

		// Save or update the cart
		return cartRepository.save(cart);
	}

	@Override
	public Cart deleteCartItem(int cartId, int itemId) throws CartNotFoundException {
		log.info("deleteCartItem method is started");
		//Item item = restTemplate.getForObject("http://localhost:9992/restaurant/item/viewById/" + itemId, Item.class);
		Cart cart = getCartById(cartId);

		List<cartItem> itemsList = cart.getItems();
		Iterator<cartItem> iterator = itemsList.iterator();

		while (iterator.hasNext()) {
			cartItem item1 = iterator.next();
			if (item1.getItemId() == itemId) {
				iterator.remove();
				break;
			}
		}

		double totalPrice = calculateTotalPrice(cart);
		cart.setTotalPrice(totalPrice);

		return updateCart(cartId, cart);
	}
	
	
	
	 @Override
	    public Cart decreaseItem(int itemId, int cartId) throws CartNotFoundException {
		 log.info("decreaseItem method is started");
	        log.info("Attempting to decrease quantity for item with ID {} in cart with ID {}", itemId, cartId);

	        Cart cart = getCartById(cartId);
	        List<cartItem> itemsList = cart.getItems();

	        for (cartItem item : itemsList) {
	            if (item.getItemId() == itemId) {
	                log.info("item found in cart. Decreasing quantity by 1 for item with ID {}", itemId);
	                int currentQuantity = item.getQuantity();
	                if(currentQuantity > 0) {
	                    item.setQuantity(currentQuantity - 1);
	                    if(item.getQuantity() == 0) {
		                	itemsList.remove(item);
		                	break;
		                }
	                }
	            }
	        }

	        // Recalculate and update the total price
	        double totalPrice = calculateTotalPrice(cart);
	        cart.setTotalPrice(totalPrice);
	        cart.setItems(itemsList);

	        // Update the cart
	        return updateCart(cartId, cart);
	    }

	// Helper method to calculate the total price of the cart
	private double calculateTotalPrice(Cart cart) {
		log.info("calculateTotalPrice method is started");
		double totalPrice = 0;
		for (cartItem item : cart.getItems()) {
			totalPrice += item.getPrice() * item.getQuantity();
		}
		return totalPrice;
	}

}
