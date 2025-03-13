package com.fds.order.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fds.order.exception.OrderException;
import com.fds.order.external.Cart;
import com.fds.order.external.Item;
import com.fds.order.external.PaymentProxy;
import com.fds.order.external.Restaurant;
import com.fds.order.external.RestaurantProoxy;
import com.fds.order.external.cartItem;
import com.fds.order.model.Order;
import com.fds.order.repo.OrderRepo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepo or;
	
	@Autowired
	PaymentProxy pp;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	RestaurantProoxy restaurantProxy;

	@Override
	public String makeOrder(Order o,int cartId) {
		log.info("makeOrder method is started");
		//boolean b = true;
		// TODO Auto-generated method stub
		// int id= Integer.parseInt(UUID.randomUUID().toString());
		Random random = new Random();
        int id;

		do {
			id = random.nextInt(9000) + 1000; // Generates a random number between 1000 and 9999 (inclusive)
		} while (String.valueOf(id).contains("0"));

	
		
		Date d = new Date();
		o.setOrderId(id);
		o.setOrderDate(d);
		Cart cart= restTemplate.getForObject("http://localhost:9993/cart/" +cartId, Cart.class);
		List<cartItem> cartlist= cart.getItems();
		List<String> itemDetails= new ArrayList<>();
		for(cartItem c:cartlist) {
			Item item= restTemplate.getForObject("http://localhost:9992/restaurant/item/viewById/" +c.getItemId(), Item.class);
			Restaurant restaurant= restaurantProxy.getRestaurantById(item.getItemId().substring(0,3));
			itemDetails.add(item.getItemName()+" \n"+"\nRestaurant: "+restaurant.getRestaurantName()+"\nItem Price:"+item.getPrice() );
		}
 		
		
		//Restaurant rest = restTemplate.getForObject("http://localhost:3334/product/getproduct/" + r.getRestaurantName(), Restaurant.class);
		//Item item = restTemplate.getForObject("http://localhost:3334/product/getproduct/" + i.getItemName(), Item.class);
		o.setOrderDetails(itemDetails);
		o.setOrderStatus("pending");
		
		
		o.setCost(cart.getTotalPrice());
		pp.doPayment(o);
		Order o1 = or.save(o);
		//log.info("*********************");
		//log.info("**************************************************************"+Double.toString(o1.getCost()));
		if (o1.getOrderId() != 0) {
			return Double.toString(o1.getCost())+Integer.toString(id);
		} else {
			throw new OrderException("not placed the order");
		}
	}

	@Override
	public Order updateOrder(int id) {
		log.info("updateOrder method is started");
		// TODO Auto-generated method stub
		Optional<Order> order= or.findById(id);
		if(order.isPresent()) {
			Order o1=order.get();
			log.info("status is successfull");
			o1.setOrderStatus("Successful");
			Order updatedOrder= or.save(o1);
			long pid= pp.getPayment(id).getBody().getTransactionId();
			pp.updatePayment(pid, updatedOrder);
			return updatedOrder;
		}else {
			throw new OrderException("update for the order with "+id+" is failed");
		}
	}

	@Override
	public String cancelOrder(int id) {
		log.info("cancelOrder method is started");
		// TODO Auto-generated method stub
		Optional<Order> order = or.findById(id);
		
		if (order.isPresent()) {
		or.deleteById(id);
		return "Order is deleted Successfully";
		}
		else {
			throw new OrderException("The Order with "+id+" is not exists");
		}
	}

	@Override
	public Order getOrder(int id) {
		log.info("getOrder method is started");
		// TODO Auto-generated method stub
		Optional<Order> order= or.findById(id);
		if(order.isPresent()) {
			return order.get();
		}else {
			throw new OrderException("Order with id "+id+" is not found");
		}
	}

	@Override
	public List<Order> getAllOrders()  {
		log.info("getAllOrders method is started");
		// TODO Auto-generated method stub
		List<Order> findAll=or.findAll();
		if(!findAll.isEmpty()) {
			return findAll;
		}else {
			throw new OrderException("No orders found");
		}
	}

	@Override
	public List<Order> getOrderByUsername(String username) {
		log.info("getOrderByOrderName method is started");
		// TODO Auto-generated method stub
		List<Order> orderList=or.findByUsername(username);
		if(!orderList.isEmpty()) {
			return orderList;
		}else {
			throw new OrderException("No order found with name: "+username);
		}
	}

	@Override
	public void orderPaymentFailed(int id) {
		log.info("updateOrder method is started");
		// TODO Auto-generated method stub
		Optional<Order> order= or.findById(id);
		if(order.isPresent()) {
			Order o1=order.get();
			log.info("status is payment failed");
			o1.setOrderStatus("Payment failed");
			long pid= pp.getPayment(id).getBody().getTransactionId();
			pp.updatePayment(pid, o1);
			or.save(o1);
			
		}
	}

}

