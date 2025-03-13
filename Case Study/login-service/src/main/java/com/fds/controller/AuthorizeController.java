package com.fds.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fds.externalClass.Cart;
import com.fds.externalClass.Item;
import com.fds.externalClass.Order;
import com.fds.externalClass.Payment;
import com.fds.externalClass.Restaurant;
import com.fds.externalServices.CartProxy;
import com.fds.externalServices.OrderProxy;
import com.fds.externalServices.PaymentProxy;
import com.fds.externalServices.RestaurantProxy;
import com.fds.model.Login;
import com.fds.service.ServiceImpl;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/registration/authorization")
public class AuthorizeController {
	
	@Autowired
	ServiceImpl serviceImpl;
	
	
	
	@Autowired
	CartProxy cartProxy;
	
	@Autowired
	OrderProxy orderProxy;
	
//	@Autowired
//	ItemProxy itemProxy;
	
	@Autowired
	RestaurantProxy restaurantProxy;
	
	@Autowired
	PaymentProxy paymentProxy;
	
	//login
	
	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("/getAllUsers")
	public ResponseEntity<List<Login>> getAllUsers(){
		log.info("Inside the getAllLUsers of AuthorizeController Class");
		log.info("Retriving the List of Logins form Database");
		return ResponseEntity.ok(serviceImpl.getAllUsers());			
	}
	
	@GetMapping("/getbyUsername/{username}")
	public ResponseEntity<Login> getbyUserName(@PathVariable String username){
		log.info("Inside the getbyusername method of Authorize Controlller");
		log.info("Retriving the userName");
		return ResponseEntity.ok(serviceImpl.getByUsername(username));
	}
	
	//@PreAuthorize("hasAnyAuthority('User', 'Admin')")
	@PutMapping("/updatetheusername/{username}")
	public ResponseEntity<Login> updateByUsername(@PathVariable String username, @RequestBody Login Login ){
		log.info("profile is Updated");
		log.info("Inside the updateByUsername method of Authorize Controller Class");
		return ResponseEntity.ok(serviceImpl.updateByUsername(username, Login));
	}
	
	@PutMapping("/updatePassword/{username}")
	public ResponseEntity<String> updatePassword(@PathVariable String username, @RequestBody Login Login ){
		log.info("Password is Updated");
		log.info("Inside the updateByUsername method of Authorize Controller Class");
		return ResponseEntity.ok(serviceImpl.updateByPassword(username, Login));
	}
	
	
	//-----------------------------------------------------//
	
	//restaurant
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping("/addRestaurant")
	public ResponseEntity<Restaurant> addRestaurant(@RequestBody @Valid Restaurant restaurant){
		log.info("Inside the addRestaurant of AuthorizeController Class");
		log.info("Adding the restaurant in db");
		return restaurantProxy.addRestaurant(restaurant);
	}
	
	@GetMapping("/viewAllRestaurants")
	public ResponseEntity<List<Restaurant>> getAllRestaurants(){
		log.info("Inside the getAllRestaurants of AuthorizeController Class");
		log.info("Retrieving all the restaurants from db");
		return restaurantProxy.getAllRestaurants();
	}
	
	@GetMapping("/viewRestaurantById/{restaurantId}")
	public ResponseEntity<Restaurant> getRestaurantById(@PathVariable String restaurantId){
		log.info("Inside the getRestaurantById of AuthorizeController Class");
		log.info("Retrieving the restaurant by id from db");
		return restaurantProxy.getRestaurantById(restaurantId);
	}
	@PreAuthorize("hasAuthority('Admin')")
	@DeleteMapping("/deleteRestaurant/{restaurantId}")
	public ResponseEntity<String> deleteRestaurantById(@PathVariable String restaurantId){
		log.info("Inside the deleteRestaurantById of AuthorizeController Class");
		log.info("Removing the restaurant by id from db");
		return restaurantProxy.deleteRestaurantById(restaurantId);
	}
	@PreAuthorize("hasAnyAuthority('User', 'Admin')")
	@GetMapping("/viewRestaurantsByLocation/{location}")
	public ResponseEntity<List<Restaurant>> getRestaurantByLocation(@PathVariable String location){
		log.info("Inside the getRestaurantByLocation of AuthorizeController Class");
		log.info("Retrieving the restaurant by location from db");
		return restaurantProxy.getRestaurantByLocation(location);
	}
	@PreAuthorize("hasAnyAuthority('User', 'Admin')")
	@GetMapping("/viewRestaurantsByName/{restaurantName}")
	public ResponseEntity<List<Restaurant>> getRestaurantByName(@PathVariable String restaurantName){
		log.info("Inside the getRestaurantByName of AuthorizeController Class");
		log.info("Retrieving the restaurant by name from db");
		return restaurantProxy.getRestaurantByName(restaurantName);
	}
	@PreAuthorize("hasAuthority('Admin')")
	@PutMapping("/updateRestaurant/{restaurantId}")
	public ResponseEntity<Restaurant> updateRestaurant(@PathVariable String restaurantId, @RequestBody Restaurant restaurant){
		log.info("Inside the updateRestaurant of AuthorizeController Class");
		log.info("Updating the restaurant ");
		return restaurantProxy.updateRestaurant(restaurantId,restaurant);
	}
	@PreAuthorize("hasAuthority('User')")
	@PutMapping("/giveRating/{restaurantId}/{rating}")
	public ResponseEntity<String> giveRating(@PathVariable String restaurantId, @PathVariable int rating){
		log.info("Inside the giveRating of AuthorizeController Class");
		log.info("Rating the restaurant ");
		return restaurantProxy.giveRating(restaurantId,rating);
	}
	
	//-------------------------------------------------------------------------//
	
	//item
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping("item/add")
	public ResponseEntity<Item> addItem(@RequestBody @Valid Item item){
		log.info("Inside the addItem of AuthorizeController Class");
		log.info("Adding the item into db");
		return restaurantProxy.addItem(item);
	}
	//@PreAuthorize("hasAnyAuthority('User', 'Admin')")
	@GetMapping("item/viewAll")
	public ResponseEntity<List<Item>> getAllItems(){
		log.info("Inside the getAllItems of AuthorizeController Class");
		log.info("Retrieving all the items from db");
		return restaurantProxy.getAllItems();
	}
	//@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("item/viewById/{itemId}")
	public ResponseEntity<Item> getItemById(@PathVariable String itemId){
		log.info("Inside the getItemById of AuthorizeController Class");
		log.info("Retrieving the item by id from db");
		return restaurantProxy.getItemById(itemId);
	}
	@PreAuthorize("hasAuthority('Admin')")
	@DeleteMapping("item/delete/{itemId}")
	public ResponseEntity<String> deleteItemById(@PathVariable String itemId){
		log.info("Inside the deleteItemById of AuthorizeController Class");
		log.info("Deleting the item by id from db");
		return restaurantProxy.deleteItemById(itemId);
	}
	
	@GetMapping("item/viewByName/{itemName}")
	public ResponseEntity<List<Item>> getItemByName(@PathVariable String itemName){
		log.info("Inside the getItemByName of AuthorizeController Class");
		log.info("Retrieving the item by name from db");
		return restaurantProxy.getItemByName(itemName);
	}
	@PreAuthorize("hasAuthority('Admin')")
	@PutMapping("item/update/{itemId}")
	public ResponseEntity<Item> updateItem(@PathVariable String itemId, @RequestBody Item item){
		log.info("Inside the updateItem of AuthorizeController Class");
		log.info("Updating the item ");
		return restaurantProxy.updateItem(itemId, item);
	}
	
	@GetMapping("/item/getItemsByrestaurantName/{restaurantName}")
	public ResponseEntity<List<Item>> getItemsByrestaurantName(@PathVariable String restaurantName){
		log.info("Inside the getItemsByrestaurantName of AuthorizeController Class");
		log.info("Retrieving the item by restaurant name from db ");
		return restaurantProxy.getItemsByrestaurantName(restaurantName);
	}
	
	//-------------------------------------------------//
	
	//cart
	@PreAuthorize("hasAnyAuthority('User', 'Admin')")
	@GetMapping("/getallcarts")
	public ResponseEntity<List<Cart>> getAllCarts(){
		log.info("Inside the getAllCarts of AuthorizeController Class");
		log.info("Retrieving all the carts from db");
		return cartProxy.getAllCarts();
	}
	
	@PostMapping("/addingitemtocart/{cartId}/{itemId}")
	public ResponseEntity<Cart> addItemToCart(@PathVariable int cartId, @PathVariable int itemId){
		log.info("Inside the addItemToCart of AuthorizeController Class");
		log.info("Adding item to the cart");
		return cartProxy.addItemToCart(cartId, itemId);
	}
	@PreAuthorize("hasAnyAuthority('User', 'Admin')")
	@GetMapping("/{cartId}")
	public ResponseEntity<Cart> getCartById(@PathVariable int cartId) {
		log.info("Inside the getCartById of AuthorizeController Class");
		log.info("Retrieving all the carts from db");
		return cartProxy.getCartById(cartId);
	}
	
	@PutMapping("/deleteItem/{cartId}/{itemId}")
	public ResponseEntity<Cart> deleteCartItem(@PathVariable int cartId, @PathVariable int itemId) {
		log.info("Inside the deleteCartItem of AuthorizeController Class");
		log.info("Deleting the item from the cart of cartdb");
		return cartProxy.deleteCartItem(cartId, itemId);
	}
	
	@PutMapping("/decreaseQuant/{itemId}/{cartId}")
	public Cart decreaseItem(@PathVariable int itemId, @PathVariable int cartId) {
		log.info("Inside the decreaseItem of AuthorizeController Class");
		log.info("Decreasing the quantity of item from the cart of cartdb");
		return cartProxy.decreaseItem(itemId, cartId);
	}
	
	@DeleteMapping("/deleteCart/{cartId}")
	public String deleteCart(@PathVariable int cartId) {
		log.info("Inside the deleteCart of AuthorizeController Class");
		log.info("deleting the cart from db");
		return cartProxy.deleteCart(cartId);
	}
	@PreAuthorize("hasAnyAuthority('User', 'Admin')")
	@PostMapping("/addCart")
	public Cart addCart(@RequestBody Cart cart) {
		log.info("Inside the addCart method of Controller");
		log.info("adding the Cart");
		return cartProxy.addCart(cart);
	}
	
	//-----------------------------------------------------//
	
	//order
	@PreAuthorize("hasAnyAuthority('User', 'Admin')")
	@PostMapping("/order/{cartId}")
	ResponseEntity<String> makeAOrder(@RequestBody Order o,@PathVariable int cartId){
		log.info("Inside the makeAOrder of AuthorizeController Class");
		log.info("Placing an order");
		return orderProxy.makeAOrder(o, cartId);
	}
	@PreAuthorize("hasAnyAuthority('User', 'Admin')")
	@DeleteMapping("/cancelOrder/{id}")
	ResponseEntity<String> cancelOrder(@PathVariable int id) {
		log.info("Inside the cancelOrder of AuthorizeController Class");
		log.info("Canceling an order");
		return orderProxy.cancelOrder(id);
	}
	
	@PutMapping("/updateOrder/{id}")
	ResponseEntity<Order> updateAnOrder(@PathVariable int id){
		log.info("Inside the updateAnOrder of AuthorizeController Class");
		log.info("updating an order by giving instructions");
		return orderProxy.updateAnOrder(id);
	}
	@PreAuthorize("hasAnyAuthority('User', 'Admin')")
	@GetMapping("/viewOrderById/{id}")
	Order viewAnOrder(@PathVariable int id) {
		log.info("Inside the viewAnOrder of AuthorizeController Class");
		log.info("Retrieving an order by id");
		return orderProxy.viewAnOrder(id);
	}
	@PreAuthorize("hasAnyAuthority('User', 'Admin')")
	@GetMapping("/viewOrderByName/{orderName}")
	List<Order> viewByOrderName(@PathVariable String orderName){
		log.info("Inside the viewByOrderName of AuthorizeController Class");
		log.info("Retrieving an order by orderName");
		return orderProxy.viewByOrderName(orderName);
	}
	@PreAuthorize("hasAnyAuthority('User', 'Admin')")
	@GetMapping("/viewAllOrders")
	List<Order> viewAllOrder(){
		log.info("Inside the viewAllOrder of AuthorizeController Class");
		log.info("Retrieving all orders");
		return orderProxy.viewAllOrder();
	}
	
	@PutMapping("/updateFailedPayment/{id}")
	public ResponseEntity<Void> updateFailedPaymetn(@PathVariable int id) {
		log.info("In the updateFailedPaymetn method in controller");
		log.info("changing status to payment failed");
		orderProxy.updateFailedPayment(id);

		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	//payment
	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("/getallpayment")
	public List<Payment> getAllPayments(){
		log.info("Inside the getAllPayments of AuthorizeController Class");
		log.info("Retrieving all payments");
		return paymentProxy.getAllPayments();
	}
	@PreAuthorize("hasAuthority('Admin')")
	@DeleteMapping("/deletePayment/{id}")
	ResponseEntity<String> deletePayment(@PathVariable long id) {
		log.info("Inside the deletePayment of AuthorizeController Class");
		log.info("Deleting the payment");
		return paymentProxy.deletePayment(id);
				
	}
	@PreAuthorize("hasAuthority('Admin')")
	@PutMapping("/updatePaymentSuccess/{id}")
	public ResponseEntity<Void> updatePaymentToSuccess(@PathVariable int id){
		return paymentProxy.updatePaymentToSuccess(id);
	}
	
	@PreAuthorize("hasAuthority('User')")
	@PostMapping("/doPayment")
	public Payment doPayment(@RequestBody Order o) {
		return paymentProxy.doPayment(o);
	}
	
	
	

}
