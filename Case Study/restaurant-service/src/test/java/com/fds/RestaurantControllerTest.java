package com.fds;

import com.fds.restaurant.controller.RestaurantController;
import com.fds.restaurant.model.Restaurant;
import com.fds.restaurant.service.RestaurantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RestaurantControllerTest {

	@InjectMocks
	private RestaurantController restaurantController;

	@Mock
	private RestaurantService restaurantService;

	@SuppressWarnings("deprecation")
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testAddRestaurant() {
		Restaurant sampleRestaurant = new Restaurant();
		sampleRestaurant.setRestaurantId("1");
		sampleRestaurant.setRestaurantName("Sample Restaurant");
		sampleRestaurant.setRating(4);
		sampleRestaurant.setType("Cuisine");
		sampleRestaurant.setLocation("Sample Location");

		when(restaurantService.addRestaurant(any())).thenReturn(sampleRestaurant);

		ResponseEntity<Restaurant> response = restaurantController.addRestaurant(sampleRestaurant);

		verify(restaurantService, times(1)).addRestaurant(any());

		assertEquals(200, response.getStatusCodeValue());
		assertEquals(sampleRestaurant, response.getBody());
	}

	@Test
	@SuppressWarnings("deprecation")
	public void testGetAllRestaurants() {
		List<Restaurant> sampleRestaurants = Arrays.asList(new Restaurant(), new Restaurant());

		when(restaurantService.getAllRestaurants()).thenReturn(sampleRestaurants);

		ResponseEntity<List<Restaurant>> response = restaurantController.getAllRestaurants();

		verify(restaurantService, times(1)).getAllRestaurants();

		assertEquals(200, response.getStatusCodeValue());
		assertEquals(sampleRestaurants, response.getBody());
	}

	@Test
	@SuppressWarnings("deprecation")
	public void testGetRestaurantById() {
		String restaurantId = "1";
		Restaurant sampleRestaurant = new Restaurant();
		sampleRestaurant.setRestaurantId(restaurantId);

		when(restaurantService.getRestaurantById(restaurantId)).thenReturn(sampleRestaurant);

		ResponseEntity<Restaurant> response = restaurantController.getRestaurantById(restaurantId);

		verify(restaurantService, times(1)).getRestaurantById(restaurantId);

		assertEquals(200, response.getStatusCodeValue());
		assertEquals(sampleRestaurant, response.getBody());
	}

	@Test
	@SuppressWarnings("deprecation")
	public void testDeleteRestaurantById() {
		String restaurantId = "1";

		when(restaurantService.deleteRestaurant(restaurantId)).thenReturn("Restaurant deleted successfully");

		ResponseEntity<String> response = restaurantController.deleteRestaurantById(restaurantId);

		verify(restaurantService, times(1)).deleteRestaurant(restaurantId);

		assertEquals(200, response.getStatusCodeValue());
		assertEquals("Restaurant deleted successfully", response.getBody());
	}

	@Test
	@SuppressWarnings("deprecation")
	void testGetRestaurantByLocation() {
		String location = "Sample Location";
		List<Restaurant> sampleRestaurants = Arrays.asList(new Restaurant(), new Restaurant());

		when(restaurantService.findByLocation(location)).thenReturn(sampleRestaurants);

		ResponseEntity<List<Restaurant>> response = restaurantController.getRestaurantByLocation(location);

		verify(restaurantService, times(1)).findByLocation(location);

		assertEquals(200, response.getStatusCodeValue());
		assertEquals(sampleRestaurants, response.getBody());
	}

	@Test
	@SuppressWarnings("deprecation")
	public void testGetRestaurantByName() {
		String restaurantName = "Sample Restaurant";
		List<Restaurant> sampleRestaurants = Arrays.asList(new Restaurant(), new Restaurant());

		when(restaurantService.findByRestaurantName(restaurantName)).thenReturn(sampleRestaurants);

		ResponseEntity<List<Restaurant>> response = restaurantController.getRestaurantByName(restaurantName);

		verify(restaurantService, times(1)).findByRestaurantName(restaurantName);

		assertEquals(200, response.getStatusCodeValue());
		assertEquals(sampleRestaurants, response.getBody());
	}

	@Test
	@SuppressWarnings("deprecation")
	public void testUpdateRestaurant() {
		String restaurantId = "1";
		Restaurant sampleRestaurant = new Restaurant();
		sampleRestaurant.setRestaurantId(restaurantId);

		when(restaurantService.updateRestaurant(restaurantId, sampleRestaurant)).thenReturn(sampleRestaurant);

		ResponseEntity<Restaurant> response = restaurantController.updateRestaurant(restaurantId, sampleRestaurant);

		verify(restaurantService, times(1)).updateRestaurant(restaurantId, sampleRestaurant);

		assertEquals(200, response.getStatusCodeValue());
		assertEquals(sampleRestaurant, response.getBody());
	}

	@Test
	@SuppressWarnings("deprecation")
	public void testGiveRating() {
		String restaurantId = "1";
		int rating = 4;

		when(restaurantService.giveTheRating(restaurantId, rating)).thenReturn("Rating updated successfully");

		ResponseEntity<String> response = restaurantController.giveRating(restaurantId, rating);

		verify(restaurantService, times(1)).giveTheRating(restaurantId, rating);

		assertEquals(200, response.getStatusCodeValue());
		assertEquals("Rating updated successfully", response.getBody());
	}
}
