//package com.fds;
//
//import com.fds.restaurant.controller.ItemController;
//import com.fds.restaurant.model.Item;
//import com.fds.restaurant.service.ItemService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.ResponseEntity;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//public class ItemControllerTest {
//
//    @InjectMocks
//    private ItemController itemController;
//
//    @Mock
//    private ItemService itemService;
//
//    @SuppressWarnings("deprecation")
//	@BeforeEach
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @SuppressWarnings("deprecation")
//	@Test
//    public void testAddItem() {
//        Item sampleItem = new Item();
//        sampleItem.setItemId("1");
//        sampleItem.setRestaurantId("2");
//        sampleItem.setItemName("Sample Item");
//        sampleItem.setCategory("Sample Category");
//        sampleItem.setDescription("Sample Description");
//        sampleItem.setPrice(10.0);
//        sampleItem.setImage("sample.jpg");
//
//        when(itemService.addItem(any())).thenReturn(sampleItem);
//
//        ResponseEntity<Item> response = itemController.addItem(sampleItem);
//
//        verify(itemService, times(1)).addItem(any());
//
//        assertEquals(200, response.getStatusCodeValue());
//        assertEquals(sampleItem, response.getBody());
//    }
//
//    @SuppressWarnings("deprecation")
//	@Test
//    public void testGetAllItems() {
//        List<Item> sampleItems = Arrays.asList(new Item(), new Item());
//
//        when(itemService.viewAllItems()).thenReturn(sampleItems);
//
//        ResponseEntity<List<Item> >response = itemController.getAllItems();
//
//        verify(itemService, times(1)).viewAllItems();
//
//        assertEquals(200, response.getStatusCodeValue());
//        assertEquals(sampleItems, response.getBody());
//    }
//
//    @SuppressWarnings("deprecation")
//	@Test
//    public void testGetItemById() {
//        String itemId = "1";
//        Item sampleItem = new Item();
//        sampleItem.setItemId(itemId);
//
//        when(itemService.viewItemById(itemId)).thenReturn(sampleItem);
//
//        ResponseEntity<Item> response = itemController.getItemById(itemId);
//
//        verify(itemService, times(1)).viewItemById(itemId);
//
//        assertEquals(200, response.getStatusCodeValue());
//        assertEquals(sampleItem, response.getBody());
//    }
//
//    @SuppressWarnings("deprecation")
//	@Test
//    public void testDeleteItemById() {
//        String itemId = "1";
//
//        when(itemService.deleteItemById(itemId)).thenReturn("Item deleted successfully");
//
//        ResponseEntity<String> response = itemController.deleteItemById(itemId);
//
//        verify(itemService, times(1)).deleteItemById(itemId);
//
//        assertEquals(200, response.getStatusCodeValue());
//        assertEquals("Item deleted successfully", response.getBody());
//    }
//
//    @SuppressWarnings("deprecation")
//	@Test
//    public void testGetItemByName() {
//        String itemName = "Sample Item";
//        List<Item> sampleItems = Arrays.asList(new Item(), new Item());
//
//        when(itemService.viewItemByName(itemName)).thenReturn(sampleItems);
//
//        ResponseEntity<List<Item>> response = itemController.getItemByName(itemName);
//
//        verify(itemService, times(1)).viewItemByName(itemName);
//
//        assertEquals(200, response.getStatusCodeValue());
//        assertEquals(sampleItems, response.getBody());
//    }
//
//    @SuppressWarnings("deprecation")
//	@Test
//    public void testUpdateItem() {
//        String itemId = "1";
//        Item sampleItem = new Item();
//        sampleItem.setItemId(itemId);
//
//        when(itemService.updateItem(itemId, sampleItem)).thenReturn(sampleItem);
//
//        ResponseEntity<Item> response = itemController.updateItem(itemId, sampleItem);
//
//        verify(itemService, times(1)).updateItem(itemId, sampleItem);
//
//        assertEquals(200, response.getStatusCodeValue());
//        assertEquals(sampleItem, response.getBody());
//    }
//
//    @SuppressWarnings("deprecation")
//	@Test
//    public void testGetItemsByrestaurantName() {
//        String restaurantName = "Sample Restaurant";
//        List<Item> sampleItems = Arrays.asList(new Item(), new Item());
//
//        when(itemService.viewByRestaurantName(restaurantName)).thenReturn(sampleItems);
//
//        ResponseEntity<List<Item> >response = itemController.getItemsByrestaurantName(restaurantName);
//
//        verify(itemService, times(1)).viewByRestaurantName(restaurantName);
//
//        assertEquals(200, response.getStatusCodeValue());
//        assertEquals(sampleItems, response.getBody());
//    }
//}
