//package com.fds.cart;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.fds.cart.entity.Cart;
//import com.fds.cart.entity.cartItem;
//import com.fds.cart.exception.CartAlreadyExistsException;
//import com.fds.cart.exception.CartNotFoundException;
//import com.fds.cart.repository.CartRepository;
//import com.fds.cart.service.CartServiceImpl;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@SpringBootTest
//public class CartRepositoryTest {
//
//    @InjectMocks
//    private CartServiceImpl cartService;
//
//    @Mock
//    private CartRepository cartRepository;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testAddCart() {
//        Cart cart = new Cart(1, new ArrayList<>(), 0.0);
//        when(cartRepository.existsById(1)).thenReturn(true);
//        assertThrows(CartAlreadyExistsException.class, () -> cartService.addCart(cart));
//
//        when(cartRepository.existsById(1)).thenReturn(false);
//        when(cartRepository.save(cart)).thenReturn(cart);
//
//        Cart savedCart = cartService.addCart(cart);
//
//        assertNotNull(savedCart);
//        assertEquals(1, savedCart.getCartId());
//    }
//
//    @Test
//    public void testGetCartById() {
//        Cart cart = new Cart(1, new ArrayList<>(), 0.0);
//        when(cartRepository.existsById(1)).thenReturn(false);
//        assertThrows(CartNotFoundException.class, () -> cartService.getCartById(1));
//
//        when(cartRepository.existsById(1)).thenReturn(true);
//        when(cartRepository.findById(1)).thenReturn((cart));
//
//        Cart result = cartService.getCartById(1);
//
//        assertNotNull(result);
//        assertEquals(1, result.getCartId());
//    }
//
//    @Test
//    public void testGetAllCarts() {
//        List<Cart> cartList = new ArrayList<>();
//        when(cartRepository.findAll()).thenReturn(cartList);
//
//        assertThrows(CartNotFoundException.class, () -> cartService.getallcarts());
//
//        Cart cart = new Cart(1, new ArrayList<>(), 0.0);
//        cartList.add(cart);
//        when(cartRepository.findAll()).thenReturn(cartList);
//
//        List<Cart> result = cartService.getallcarts();
//
//        assertNotNull(result);
//        assertFalse(result.isEmpty());
//    }
//
//    @Test
//    public void testUpdateCart() {
//        Cart cart = new Cart(1, new ArrayList<>(), 0.0);
//        when(cartRepository.existsById(1)).thenReturn(false);
//        assertThrows(CartNotFoundException.class, () -> cartService.updateCart(1, cart));
//
//        when(cartRepository.existsById(1)).thenReturn(true);
//        when(cartRepository.findById(1)).thenReturn((cart));
//        when(cartRepository.save(cart)).thenReturn(cart);
//
//        Cart updatedCart = cartService.updateCart(1, cart);
//
//        assertNotNull(updatedCart);
//        assertEquals(1, updatedCart.getCartId());
//    }
//
//
//
//    @Test
//    public void testCartTotal() {
//        Cart cart = new Cart(1, new ArrayList<>(), 100.0);
//
//        double total = cartService.cartTotal(cart);
//
//        assertEquals(100.0, total);
//    }
//
//
//
//    @Test
//    public void testDeleteCartItem() {
//        Cart cart = new Cart(1, new ArrayList<>(), 0.0);
//        cart.getItems().add(new cartItem(1, "Item1", "Info1", 10.0, 1, "Image1"));
//
//        when(cartRepository.existsById(1)).thenReturn(true);
//        when(cartRepository.findById(1)).thenReturn(cart);
//
//        Cart result = cartService.deleteCartItem(1, 1);
//
//        assertNotNull(result);
//        assertTrue(result.getItems().isEmpty());
//    }
//
//    @Test
//    public void testDecreaseItem() {
//        Cart cart = new Cart(1, new ArrayList<>(), 0.0);
//        cart.getItems().add(new cartItem(1, "Item1", "Info1", 10.0, 2, "Image1"));
//
//        when(cartRepository.existsById(1)).thenReturn(true);
//        when(cartRepository.findById(1)).thenReturn(cart);
//
//        Cart result = cartService.decreaseItem(1, 1);
//
//        assertNotNull(result);
//        assertTrue(result.getItems().get(0).getQuantity() == 1);
//    }
//}
