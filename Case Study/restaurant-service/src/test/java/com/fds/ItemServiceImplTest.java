package com.fds;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.fds.restaurant.exception.ItemException;
import com.fds.restaurant.model.Item;
import com.fds.restaurant.model.Restaurant;
import com.fds.restaurant.repository.ItemRepository;
import com.fds.restaurant.repository.RestaurantRepo;
import com.fds.restaurant.service.ItemServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ItemServiceImplTest {

    @InjectMocks
    private ItemServiceImpl itemService;

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private RestaurantRepo restaurantRepo;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddItem() {
        Item item = new Item("1", "1", "Item1", "Category1", "Description1", 10.0, "image1");
        when(restaurantRepo.findAll()).thenReturn(new ArrayList<>());
        assertThrows(ItemException.class, () -> itemService.addItem(item));

        Restaurant restaurant = new Restaurant("1", "Restaurant1", 4, "Type1", "Location1");
        when(restaurantRepo.findAll()).thenReturn(List.of(restaurant));
        when(itemRepository.save(item)).thenReturn(item);

        Item savedItem = itemService.addItem(item);

        assertNotNull(savedItem);
        assertEquals(item.getItemId(), savedItem.getItemId());
    }



    @Test
    public void testViewItemByName() {
        String itemName = "Item1";
        List<Item> itemList = new ArrayList<>();
        when(itemRepository.findByItemName(itemName)).thenReturn(itemList);

        assertThrows(ItemException.class, () -> itemService.viewItemByName(itemName));
    }

    @Test
    public void testDeleteItemById() {
        String itemId = "1";
        when(itemRepository.findById(itemId)).thenReturn(Optional.empty());

        assertThrows(ItemException.class, () -> itemService.deleteItemById(itemId));

        Item item = new Item("1", "1", "Item1", "Category1", "Description1", 10.0, "image1");
        when(itemRepository.findById(itemId)).thenReturn(Optional.of(item));
        doNothing().when(itemRepository).deleteById(itemId);

        String result = itemService.deleteItemById(itemId);

        assertEquals("item is deleted Successfully", result);
    }

    @Test
    public void testUpdateItem() {
        String itemId = "1";
        Item item = new Item("1", "1", "Item1", "Category1", "Description1", 10.0, "image1");
        when(itemRepository.findById(itemId)).thenReturn(Optional.empty());

        assertThrows(ItemException.class, () -> itemService.updateItem(itemId, item));

        Item existingItem = new Item("1", "1", "Item1", "Category1", "Description1", 10.0, "image1");
        when(itemRepository.findById(itemId)).thenReturn(Optional.of(existingItem));
        when(itemRepository.save(existingItem)).thenReturn(existingItem);

        Item updatedItem = itemService.updateItem(itemId, item);

        assertNotNull(updatedItem);
        assertEquals(existingItem.getItemId(), updatedItem.getItemId());
        assertEquals(item.getItemName(), updatedItem.getItemName());
        assertEquals(item.getDescription(), updatedItem.getDescription());
        assertEquals(item.getPrice(), updatedItem.getPrice());
        assertEquals(item.getImage(), updatedItem.getImage());
    }


    @Test
    public void testViewByRestaurantName() {
        String restaurantName = "Restaurant1";
        Restaurant restaurant = new Restaurant("1", "Restaurant1", 4, "Type1", "Location1");
        when(restaurantRepo.findByRestaurantName(restaurantName)).thenReturn(List.of(restaurant));

        Item item1 = new Item("1", "1", "Item1", "Category1", "Description1", 10.0, "image1");
        Item item2 = new Item("2", "1", "Item2", "Category2", "Description2", 15.0, "image2");
        when(itemRepository.findByRestaurantId("1")).thenReturn(List.of(item1, item2));

        List<Item> result = itemService.viewByRestaurantName(restaurantName);

        assertNotNull(result);
        assertEquals(2, result.size());
    }
}
