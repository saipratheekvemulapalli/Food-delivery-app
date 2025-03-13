package com.fds;

import com.fds.restaurant.exception.RestaurantNotFoundException;
import com.fds.restaurant.model.Restaurant;
import com.fds.restaurant.repository.RestaurantRepo;
import com.fds.restaurant.service.RestaurantServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RestaurantServiceImplTest {

    @InjectMocks
    private RestaurantServiceImpl restaurantService;

    @Mock
    private RestaurantRepo restaurantRepo;

    @SuppressWarnings("deprecation")
	@BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddRestaurant() {
        Restaurant sampleRestaurant = new Restaurant();
        sampleRestaurant.setRestaurantId("1");
        sampleRestaurant.setRestaurantName("Sample Restaurant");
        sampleRestaurant.setType("Sample Type");
        sampleRestaurant.setLocation("Sample Location");
        sampleRestaurant.setRating(4);

        when(restaurantRepo.save(any())).thenReturn(sampleRestaurant);

        Restaurant response = restaurantService.addRestaurant(sampleRestaurant);

        verify(restaurantRepo, times(1)).save(any());

        assertEquals(sampleRestaurant, response);
    }


    @Test
    public void testGetAllRestaurants() {
        List<Restaurant> sampleRestaurants = Arrays.asList(new Restaurant(), new Restaurant());

        when(restaurantRepo.findAll()).thenReturn(sampleRestaurants);

        List<Restaurant> response = restaurantService.getAllRestaurants();

        verify(restaurantRepo, times(1)).findAll();

        assertEquals(sampleRestaurants, response);
    }

    @Test
    public void testGetAllRestaurantsEmptyList() {
        List<Restaurant> emptyList = Collections.emptyList();

        when(restaurantRepo.findAll()).thenReturn(emptyList);

        assertThrows(RestaurantNotFoundException.class, () -> {
            restaurantService.getAllRestaurants();
        });
    }

    @Test
    public void testGetRestaurantById() {
        String restaurantId = "1";
        Restaurant sampleRestaurant = new Restaurant();
        sampleRestaurant.setRestaurantId(restaurantId);

        when(restaurantRepo.findById(restaurantId)).thenReturn(Optional.of(sampleRestaurant));

        Restaurant response = restaurantService.getRestaurantById(restaurantId);

        verify(restaurantRepo, times(1)).findById(restaurantId);

        assertEquals(sampleRestaurant, response);
    }

    @Test
    public void testGetRestaurantByIdNotFound() {
        String restaurantId = "1";

        when(restaurantRepo.findById(restaurantId)).thenReturn(Optional.empty());

        assertThrows(RestaurantNotFoundException.class, () -> {
            restaurantService.getRestaurantById(restaurantId);
        });
    }

    @Test
    public void testDeleteRestaurant() {
        String restaurantId = "1";
        Restaurant sampleRestaurant = new Restaurant();

        when(restaurantRepo.findById(restaurantId)).thenReturn(Optional.of(sampleRestaurant));

        String response = restaurantService.deleteRestaurant(restaurantId);

        verify(restaurantRepo, times(1)).deleteById(restaurantId);

        assertEquals("Restaurant is deleted Successfully", response);
    }

    @Test
    public void testDeleteRestaurantNotFound() {
        String restaurantId = "1";

        when(restaurantRepo.findById(restaurantId)).thenReturn(Optional.empty());

        assertThrows(RestaurantNotFoundException.class, () -> {
            restaurantService.deleteRestaurant(restaurantId);
        });
    }

    @Test
    public void testFindByLocation() {
        String location = "Sample Location";
        List<Restaurant> sampleRestaurants = Arrays.asList(new Restaurant(), new Restaurant());

        when(restaurantRepo.findByLocation(location)).thenReturn(sampleRestaurants);

        List<Restaurant> response = restaurantService.findByLocation(location);

        verify(restaurantRepo, times(1)).findByLocation(location);

        assertEquals(sampleRestaurants, response);
    }

    @Test
    public void testFindByLocationNotFound() {
        String location = "Sample Location";

        when(restaurantRepo.findByLocation(location)).thenReturn(Collections.emptyList());

        assertThrows(RestaurantNotFoundException.class, () -> {
            restaurantService.findByLocation(location);
        });
    }

    @Test
    public void testFindByRestaurantName() {
        String restaurantName = "Sample Restaurant";
        List<Restaurant> sampleRestaurants = Arrays.asList(new Restaurant(), new Restaurant());

        when(restaurantRepo.findByRestaurantName(restaurantName)).thenReturn(sampleRestaurants);

        List<Restaurant> response = restaurantService.findByRestaurantName(restaurantName);

        verify(restaurantRepo, times(1)).findByRestaurantName(restaurantName);

        assertEquals(sampleRestaurants, response);
    }

    @Test
    public void testFindByRestaurantNameNotFound() {
        String restaurantName = "Sample Restaurant";

        when(restaurantRepo.findByRestaurantName(restaurantName)).thenReturn(Collections.emptyList());

        assertThrows(RestaurantNotFoundException.class, () -> {
            restaurantService.findByRestaurantName(restaurantName);
        });
    }

    @Test
    public void testUpdateRestaurant() {
        String restaurantId = "1";
        Restaurant sampleRestaurant = new Restaurant();
        sampleRestaurant.setRestaurantName("Updated Restaurant");
        sampleRestaurant.setType("Updated Type");
        sampleRestaurant.setLocation("Updated Location");
        sampleRestaurant.setRating(5);

        when(restaurantRepo.findById(restaurantId)).thenReturn(Optional.of(sampleRestaurant));
        when(restaurantRepo.save(any())).thenReturn(sampleRestaurant);

        Restaurant response = restaurantService.updateRestaurant(restaurantId, sampleRestaurant);

        verify(restaurantRepo, times(1)).findById(restaurantId);
        verify(restaurantRepo, times(1)).save(any());

        assertEquals(sampleRestaurant, response);
    }

    @Test
    public void testUpdateRestaurantNotFound() {
        String restaurantId = "1";
        Restaurant sampleRestaurant = new Restaurant();

        when(restaurantRepo.findById(restaurantId)).thenReturn(Optional.empty());

        assertThrows(RestaurantNotFoundException.class, () -> {
            restaurantService.updateRestaurant(restaurantId, sampleRestaurant);
        });
    }

    @Test
    public void testGiveTheRating() {
        String restaurantId = "1";
        int rating = 4;
        Restaurant sampleRestaurant = new Restaurant();
        sampleRestaurant.setRating(rating);

        when(restaurantRepo.findById(restaurantId)).thenReturn(Optional.of(sampleRestaurant));

        String response = restaurantService.giveTheRating(restaurantId, rating);

        verify(restaurantRepo, times(1)).findById(restaurantId);

        assertEquals("The rating is given", response);
    }


}
