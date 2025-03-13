package com.fds;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.fds.restaurant.exception.RestaurantNotFoundException;
import com.fds.restaurant.model.Restaurant;
import com.fds.restaurant.repository.RestaurantRepo;
import com.fds.restaurant.service.RestaurantServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class RestaurantRepoTest {

    @InjectMocks
    private RestaurantServiceImpl restaurantService;

    @Mock
    private RestaurantRepo restaurantRepo;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddRestaurant() {
        Restaurant restaurant = new Restaurant("1", "Restaurant1", 4, "Type1", "Location1");
        when(restaurantRepo.save(restaurant)).thenReturn(restaurant);

        Restaurant savedRestaurant = restaurantService.addRestaurant(restaurant);

        assertNotNull(savedRestaurant);
        assertEquals(restaurant.getRestaurantId(), savedRestaurant.getRestaurantId());
    }



    @Test
    public void testGetRestaurantById() {
        String restaurantId = "1";
        when(restaurantRepo.findById(restaurantId)).thenReturn(Optional.empty());

        assertThrows(RestaurantNotFoundException.class, () -> restaurantService.getRestaurantById(restaurantId));

        Restaurant restaurant = new Restaurant("1", "Restaurant1", 4, "Type1", "Location1");
        when(restaurantRepo.findById(restaurantId)).thenReturn(Optional.of(restaurant));

        Restaurant result = restaurantService.getRestaurantById(restaurantId);

        assertNotNull(result);
        assertEquals(restaurant.getRestaurantId(), result.getRestaurantId());
    }

    @Test
    public void testDeleteRestaurant() {
        String restaurantId = "1";
        when(restaurantRepo.findById(restaurantId)).thenReturn(Optional.empty());

        assertThrows(RestaurantNotFoundException.class, () -> restaurantService.deleteRestaurant(restaurantId));

        Restaurant restaurant = new Restaurant("1", "Restaurant1", 4, "Type1", "Location1");
        when(restaurantRepo.findById(restaurantId)).thenReturn(Optional.of(restaurant));
        doNothing().when(restaurantRepo).deleteById(restaurantId);

        String result = restaurantService.deleteRestaurant(restaurantId);

        assertEquals("Restaurant is deleted Successfully", result);
    }

    @Test
    public void testFindByLocation() {
        String location = "Location1";
        List<Restaurant> restaurantList = new ArrayList<>();
        when(restaurantRepo.findByLocation(location)).thenReturn(restaurantList);

        assertThrows(RestaurantNotFoundException.class, () -> restaurantService.findByLocation(location));
    }

    @Test
    public void testFindByRestaurantName() {
        String restaurantName = "Restaurant1";
        List<Restaurant> restaurantList = new ArrayList<>();
        when(restaurantRepo.findByRestaurantName(restaurantName)).thenReturn(restaurantList);

        assertThrows(RestaurantNotFoundException.class, () -> restaurantService.findByRestaurantName(restaurantName));
    }

    @Test
    public void testUpdateRestaurant() {
        String restaurantId = "1";
        Restaurant restaurant = new Restaurant("1", "Restaurant1", 4, "Type1", "Location1");
        when(restaurantRepo.findById(restaurantId)).thenReturn(Optional.empty());

        assertThrows(RestaurantNotFoundException.class, () -> restaurantService.updateRestaurant(restaurantId, restaurant));

        Restaurant existingRestaurant = new Restaurant("1", "Restaurant1", 4, "Type1", "Location1");
        when(restaurantRepo.findById(restaurantId)).thenReturn(Optional.of(existingRestaurant));
        when(restaurantRepo.save(existingRestaurant)).thenReturn(existingRestaurant);

        Restaurant updatedRestaurant = restaurantService.updateRestaurant(restaurantId, restaurant);

        assertNotNull(updatedRestaurant);
        assertEquals(existingRestaurant.getRestaurantId(), updatedRestaurant.getRestaurantId());
        assertEquals(restaurant.getRestaurantName(), updatedRestaurant.getRestaurantName());
        assertEquals(restaurant.getType(), updatedRestaurant.getType());
        assertEquals(restaurant.getLocation(), updatedRestaurant.getLocation());
        assertEquals(restaurant.getRating(), updatedRestaurant.getRating());
    }


}
