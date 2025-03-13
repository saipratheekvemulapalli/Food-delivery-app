//package com.fds.cart;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//
//import com.fds.cart.entity.Cart;
//import com.fds.cart.entity.ErrorResponse;
//import com.fds.cart.entity.Item;
//import com.fds.cart.entity.cartItem;
//import com.fds.cart.exception.CartNotFoundException;
//import com.fds.cart.repository.CartRepository;
//import com.fds.cart.service.CartServiceImpl;
//
//@SpringBootTest
//class CartServiceTest {
//
//	@Autowired
//	private CartServiceImpl cartServiceImpl;
//	@MockBean
//	private CartRepository cartRepository;
//
//	@Test
//	public void getallCart_test() {
//		List<Cart> cart = new ArrayList<>();
//		Cart c = new Cart();
//
//		c.setCartId(11);
//		c.setTotalPrice(100);
//		Cart c1=new Cart();
//		c1.setCartId(12);
//		c1.setTotalPrice(500);
//		cart.add(c1);
//		cart.add(c);
//		when(cartRepository.findAll()).thenReturn(cart);
//		assertEquals(2, cartServiceImpl.getallcarts().size());
//
//	}
//
//	@Test
//	public void getCartById_test() {
//
//		Cart c = new Cart();
//		c.setCartId(11);
//		c.setTotalPrice(100);
//
//		when(cartRepository.existsById(11)).thenReturn(true);
//		when(cartRepository.findById(11)).thenReturn(c);
//		assertEquals(c, cartServiceImpl.getCartById(11));
//	}
//
//	@Test
//	public void addCart_test() {
//
//		Cart c2 = new Cart();
//		c2.setCartId(10);
//		c2.setTotalPrice(1200);
//
//		when(cartRepository.existsById(10)).thenReturn(false);
//		when(cartRepository.save(c2)).thenReturn(c2);
//		assertEquals(c2, cartServiceImpl.addCart(c2));
//	}
//
//	@Test
//	public void updateCart_test() {
//
//		Cart c3 = new Cart();
//		Cart c4 = new Cart();
//
//		c3.setCartId(14);
//		c3.setTotalPrice(500.00);
//
//		c4.setCartId(14);
//		c4.setTotalPrice(700.00);
//
//		//Optional<Cart> cart = Optional.of(c3);
//
//		when(cartRepository.existsById(14)).thenReturn(true);
//		when(cartRepository.findById(14)).thenReturn(c4);
//		when(cartRepository.save(c4)).thenReturn(c4);
//		assertEquals(c4, cartServiceImpl.updateCart(14, c4));
//	}
//
//	@Test
//	public void deleteCart_test() {
//
//		Cart c3 = new Cart();
//		c3.setCartId(10);
//		c3.setTotalPrice(1200);
//
//		when(cartRepository.existsById(10)).thenReturn(true);
//		when(cartRepository.findById(10)).thenReturn(c3);
//		assertEquals("Deleted Successfully", cartServiceImpl.deleteCartById(10));
//	}
//
//	@Test
//	public void cartTotal_test() {
//
//		Cart c3 = new Cart();
//		c3.setCartId(10);
//		c3.setTotalPrice(1200);
//
//		when(cartRepository.existsById(10)).thenReturn(true);
//		when(cartRepository.findById(10)).thenReturn(c3);
//		assertEquals(1200, cartServiceImpl.cartTotal(c3));
//	}
//
//	@Test
//	public void getCartByIdException_test() {
//	    Cart c1 = new Cart();
//	    c1.setCartId(11); // Set the cartId to match the query
//	    c1.setTotalPrice(1200);
//
//	    when(cartRepository.existsById(11)).thenReturn(true);
//	    when(cartRepository.findById(11)).thenReturn(c1);
//
//	    Cart returnedCart = cartServiceImpl.getCartById(11);
//
//	    assertEquals(c1, returnedCart);
//	    assertEquals(11, returnedCart.getCartId()); // Ensure cartId is as expected
//	}
//
//
//	@Test
//	public void updateCartException_test() {
//	    Cart originalCart = new Cart();
//	    originalCart.setCartId(11); // Set the cartId to match the query
//	    originalCart.setTotalPrice(1200);
//
//	    Cart updatedCart = new Cart();
//	    updatedCart.setCartId(11); // Set the cartId to match the query
//	    updatedCart.setTotalPrice(1500); // Set an updated total price
//
//	    when(cartRepository.existsById(11)).thenReturn(true);
//	    when(cartRepository.findById(11)).thenReturn(originalCart);
//	    when(cartRepository.save(updatedCart)).thenReturn(updatedCart);
//
//	    Cart returnedCart = cartServiceImpl.updateCart(11, updatedCart);
//
//	    assertEquals(updatedCart, returnedCart);
//	    assertEquals(11, returnedCart.getCartId()); // Ensure cartId is as expected
//	    assertEquals(1500, returnedCart.getTotalPrice()); // Ensure total price is as expected
//	}
//
//
//	@Test
//	public void getAllCartException_test() {
//	    List<Cart> carts = new ArrayList<>();
//	    Cart c = new Cart();
//	    carts.add(c); // Add a cart to the list
//
//	    when(cartRepository.findAll()).thenReturn(carts);
//
//	    List<Cart> returnedCarts = cartServiceImpl.getallcarts();
//
//	    assertEquals(1, returnedCarts.size()); // Check the size of the returned cart list
//	}
//
//
//	
//	@Test
//	public void item_test() {
//		Item i = new Item();
//		i.setItemId("100");
//		i.setItemName("cake");
//		i.setDescription("red velvet");
//		i.setPrice(625);
//		
//
//	}
//
//	
//
//	@Test
//	public void cartitem_test() {
//		cartItem i = new cartItem();
//		i.setImage("img");
//		i.setPrice(150.0);
//		i.setItemId(101);
//		i.setItemName("noodles");
//		i.setQuantity(2);
//	}
//
//	@Test
//	public void items_test() {
//		cartItem i = new cartItem(1, "cake","cheese cake", 600, 1, "img");
//
//		i.setImage("img");
//		i.setPrice(150.0);
//		i.setItemId(100);
//		i.setItemName("noodles");
//		i.setQuantity(2);
//	}
//
//	@Test
//	public void ErrorResponse_test() {
//		ErrorResponse e = new ErrorResponse();
//		e.setStatusmessage(HttpStatus.OK);
//		e.setDatetime(LocalDateTime.now());
//		e.setMessage("not null");
//	}
//
//	@Test
//	public void ErrorResponses_test() {
//		ErrorResponse e = new ErrorResponse(HttpStatus.OK, "Not null", LocalDateTime.now());
//		e.setStatusmessage(HttpStatus.OK);
//		e.setDatetime(LocalDateTime.now());
//		e.setMessage("not null");
//	}
//
//	
//	
//	@Test
//    public void cartConstructor_test() {
//        List<cartItem> it = new ArrayList<>();
//        cartItem items = new cartItem(1, "Biryani","chicken dum biryani single", 180, 1, "img");
//        it.add(items);
//        Cart c = new Cart(1, it, 120);
//        c.setCartId(1);
//        c.setItems(it);
//        c.setTotalPrice(120);
//    }
//	
//	@Test
//	 public void cartDefaultConstructor_test() {
//        List<cartItem> it = new ArrayList<>();
//        cartItem items = new cartItem();
//        it.add(items);
//        Cart c = new Cart();
//        c.setCartId(1);
//        c.setItems(it);
//        c.setTotalPrice(120);
//    }
//	
//    @Test
//    void getCartById_CartExists_ReturnsCart() throws CartNotFoundException {
//        // Arrange
//        int cartId = 1;
//        Cart cart = new Cart();
//        cart.setCartId(cartId);
//
//        // Mock the behavior of the cartRepository
//        when(cartRepository.findById(cartId)).thenReturn(cart);
//
//        // Act
//        Cart result = cartServiceImpl.getCartById(cartId);
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(cartId, result.getCartId());
//    }
//
//    @Test
//    void getCartById_CartDoesNotExist_ThrowsCartNotFoundException() {
//        // Arrange
//        int cartId = 1;
//        when(cartRepository.findById(cartId)).thenReturn(null);
//
//        // Act and Assert
//        assertThrows(CartNotFoundException.class, () -> cartServiceImpl.getCartById(cartId));
//    }
//
//    @Test
//    void updateCart_CartExists_ReturnsUpdatedCart() throws CartNotFoundException {
//        // Arrange
//        int cartId = 1;
//        Cart originalCart = new Cart();
//        originalCart.setCartId(cartId);
//
//        Cart updatedCart = new Cart();
//        updatedCart.setCartId(cartId);
//
//        when(cartRepository.existsById(cartId)).thenReturn(true);
//        when(cartRepository.findById(cartId)).thenReturn(originalCart);
//        when(cartRepository.save(any(Cart.class))).thenReturn(updatedCart);
//
//        // Act
//        Cart result = cartServiceImpl.updateCart(cartId, updatedCart);
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(cartId, result.getCartId());
//    }
//
//	
//
//}
