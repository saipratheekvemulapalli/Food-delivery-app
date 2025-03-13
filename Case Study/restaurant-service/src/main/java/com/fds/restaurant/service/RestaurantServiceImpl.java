package com.fds.restaurant.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fds.restaurant.exception.RestaurantNotFoundException;
import com.fds.restaurant.model.Item;
import com.fds.restaurant.model.Restaurant;
import com.fds.restaurant.repository.RestaurantRepo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RestaurantServiceImpl implements RestaurantService{

	@Autowired
	private RestaurantRepo restaurantRepo;
	
	@Autowired
	private ItemServiceImpl itemServiceImpl;
	
	@Override
	public Restaurant addRestaurant(Restaurant restaurant) {
		// TODO Auto-generated method stub
		log.info("Add method is started");
		if (restaurant.getRestaurantId().isEmpty() ||
				restaurant.getRestaurantName().isEmpty() ||
				restaurant.getType().isEmpty() ||
				restaurant.getLocation().isEmpty() ||
				restaurant.getRating()<0) {
		    	log.info("Inside the if condition of Add method");
		        throw new RestaurantNotFoundException("Please fill every field appropriately");
		   }
		    else {
		    log.info("Inside the else condition of Add method");
		    return restaurantRepo.save(restaurant);
		    }
	}

	@Override
	public List<Restaurant> getAllRestaurants() {
		// TODO Auto-generated method stub
		log.info("Get All restaurants" );
		List<Restaurant> findAll=restaurantRepo.findAll();
		if(!findAll.isEmpty()) {
			return findAll;
		}else {
			throw new RestaurantNotFoundException("No restaurants found");
		}
	}

	@Override
	public Restaurant getRestaurantById(String restaurantId) {
		// TODO Auto-generated method stub
		log.info("Get restaurant by id ");
		Optional<Restaurant> restaurant= restaurantRepo.findById(restaurantId);
		if(restaurant.isPresent()) {
			return restaurant.get();
		}else {
			throw new RestaurantNotFoundException("Restaurant with id "+restaurantId+" is not found");
		}
	}

	@Override
	public String deleteRestaurant(String restaurantId) {
		log.info("Delete Restaurant Method Started");
		Optional<Restaurant> restaurant = restaurantRepo.findById(restaurantId);
		
		if (restaurant.isPresent()) {
		restaurantRepo.deleteById(restaurantId);
		List<Item> itemlist = itemServiceImpl.viewAllItems();
		for(Item item:itemlist) {
			if(item.getItemId().startsWith(restaurantId)) {
				itemServiceImpl.deleteItemById(item.getItemId());
			}
		}
		
		return "Restaurant is deleted Successfully";
		}
		else {
			throw new RestaurantNotFoundException("The Restaurant with "+restaurantId+" is not exists");
		}
	}

	@Override
	public List<Restaurant> findByLocation(String location) {
		// TODO Auto-generated method stub
		log.info("Get restaurant by location");
		List<Restaurant> restaurants =restaurantRepo.findByLocation(location);
		if(restaurants.isEmpty()) {
			throw new RestaurantNotFoundException("There are no restaurants in that area");
		}else {
			return restaurants;
		}
	}

	@Override
	public List<Restaurant> findByRestaurantName(String restaurantName) {
		// TODO Auto-generated method stub
		log.info("Get restaurant by it's name");
		List<Restaurant> restaurants=restaurantRepo.findByRestaurantName(restaurantName);
		if(restaurants.isEmpty()) {
			throw new RestaurantNotFoundException("There are no restaurants with that name");
		}else {
			return restaurants;
		}
		
	}

	@Override
	public Restaurant updateRestaurant(String restaurantId, Restaurant restaurant) {
		// TODO Auto-generated method stub
		log.info("Updating the restaurant");
		Optional<Restaurant> r = restaurantRepo.findById(restaurantId);
		if(r.isPresent()) {
			Restaurant restaurant2=r.get();
			restaurant2.setRestaurantName(restaurant.getRestaurantName());
			restaurant2.setType(restaurant.getType());
			restaurant2.setLocation(restaurant.getLocation());
			restaurant2.setRating(restaurant.getRating());
			
			Restaurant updatedRestaurant=restaurantRepo.save(restaurant2);
			return updatedRestaurant;
		}else {
			throw new RestaurantNotFoundException("The restaurant is not found");
		}
	}

	@Override
	public String giveTheRating(String restaurantId,int rating) {
		// TODO Auto-generated method stub
		log.info("giving the rating to the restaurant");
		Optional<Restaurant> r = restaurantRepo.findById(restaurantId);
		if(r.isPresent()) {
			Restaurant restaurant2=r.get();
			restaurant2.setRating(rating);
			return "The rating is given";
			
		}else {
			return "The rating is of the restaurant "+r.get().getRestaurantName()+" is still "+r.get().getRating();
		}
	}

}
