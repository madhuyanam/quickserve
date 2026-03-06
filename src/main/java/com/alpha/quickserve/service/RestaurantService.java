package com.alpha.quickserve.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alpha.quickserve.entity.Item;
import com.alpha.quickserve.entity.Order;
import com.alpha.quickserve.entity.Restaurant;
import com.alpha.quickserve.exception.ItemNotFoundException;
import com.alpha.quickserve.exception.OrderNotFoundException;
import com.alpha.quickserve.exception.RestaurantNotFoundException;
import com.alpha.quickserve.repository.ItemRepository;
import com.alpha.quickserve.repository.OrderRepository;
import com.alpha.quickserve.repository.RestaurantRepository;
import com.alpha.quickserve.responcestructure.ResponceStructure;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepo;

    @Autowired
    private ItemRepository itemRepo;

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private RedisService redisService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    // Register Restaurant
    public ResponseEntity<ResponceStructure<Restaurant>> register(Restaurant restaurant){

        Restaurant savedRestaurant = restaurantRepo.save(restaurant);

        ResponceStructure<Restaurant> rs = new ResponceStructure<>();
        rs.setStatusCode(HttpStatus.CREATED.value());
        rs.setMessage("Restaurant Registered Successfully");
        rs.setData(savedRestaurant);

        return new ResponseEntity<>(rs,HttpStatus.CREATED);
    }

    // Find Restaurant
    public ResponseEntity<ResponceStructure<Restaurant>> findRestaurant(long mobno){

        Restaurant restaurant = restaurantRepo.findByMobno(mobno)
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found"));

        ResponceStructure<Restaurant> rs = new ResponceStructure<>();
        rs.setStatusCode(HttpStatus.OK.value());
        rs.setMessage("Restaurant Found");
        rs.setData(restaurant);

        return new ResponseEntity<>(rs,HttpStatus.OK);
    }

    // Delete Restaurant
    public ResponseEntity<ResponceStructure<String>> deleteRestaurant(long mobno){

        Restaurant restaurant = restaurantRepo.findByMobno(mobno)
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found"));

        restaurantRepo.delete(restaurant);

        ResponceStructure<String> rs = new ResponceStructure<>();
        rs.setStatusCode(HttpStatus.OK.value());
        rs.setMessage("Restaurant Deleted Successfully");
        rs.setData("Deleted");

        return new ResponseEntity<>(rs,HttpStatus.OK);
    }

    // Add Item To Menu
    public ResponseEntity<ResponceStructure<Restaurant>> addItemToMenu(long mobno,Item item){

        Restaurant restaurant = restaurantRepo.findByMobno(mobno)
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found"));

        restaurant.getMenuItems().add(item);

        restaurantRepo.save(restaurant);

        ResponceStructure<Restaurant> rs = new ResponceStructure<>();
        rs.setStatusCode(HttpStatus.OK.value());
        rs.setMessage("Item Added To Menu");
        rs.setData(restaurant);

        return new ResponseEntity<>(rs,HttpStatus.OK);
    }

    // Update Restaurant Status
    public ResponseEntity<ResponceStructure<String>> updateStatus(long mobno,String status){

        Restaurant restaurant = restaurantRepo.findByMobno(mobno)
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found"));

        restaurant.setStatus(status);

        restaurantRepo.save(restaurant);

        ResponceStructure<String> rs = new ResponceStructure<>();
        rs.setStatusCode(HttpStatus.OK.value());
        rs.setMessage("Restaurant Status Updated");
        rs.setData(status);

        return new ResponseEntity<>(rs,HttpStatus.OK);
    }

    // Update Item Availability
    public ResponseEntity<ResponceStructure<String>> updateItemAvailability(int itemid,String availability){

        Item item = itemRepo.findById(itemid)
                .orElseThrow(() -> new ItemNotFoundException("Item not found"));

        item.setAvailability(availability);

        itemRepo.save(item);

        ResponceStructure<String> rs = new ResponceStructure<>();
        rs.setStatusCode(HttpStatus.OK.value());
        rs.setMessage("Item Availability Updated");
        rs.setData(availability);

        return new ResponseEntity<>(rs,HttpStatus.OK);
    }

    // Find Nearby Delivery Partners
    public ResponseEntity<ResponceStructure<List<String>>> findNearbyDeliveryPartners(
            double latitude,double longitude){

        List<String> partners = redisService.findNearbyPartners(latitude,longitude,5.0);

        ResponceStructure<List<String>> rs = new ResponceStructure<>();
        rs.setStatusCode(HttpStatus.OK.value());
        rs.setMessage("Nearby Delivery Partners");
        rs.setData(partners);

        return new ResponseEntity<>(rs,HttpStatus.OK);
    }

    // Accept Order
    public ResponseEntity<ResponceStructure<List<String>>> acceptOrder(
            double latitude,double longitude,Integer orderid){

        Order order = orderRepo.findById(orderid)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));

        List<String> nearbyPartners =
                redisService.findNearbyPartners(latitude,longitude,5.0);

        String orderKey = "order:"+orderid;

        for(String partnerid : nearbyPartners){
            redisTemplate.opsForSet().add(orderKey,partnerid);
        }

        ResponceStructure<List<String>> rs = new ResponceStructure<>();
        rs.setStatusCode(HttpStatus.OK.value());
        rs.setMessage("Order sent to nearby delivery partners");
        rs.setData(nearbyPartners);

        return new ResponseEntity<>(rs,HttpStatus.OK);
    }

    //remove item from the menu
    public ResponseEntity<ResponceStructure<String>> removeItemFromMenu(
            long mobno,
            int itemid){

        Restaurant restaurant = restaurantRepo.findByMobno(mobno)
                .orElseThrow(() ->
                        new RestaurantNotFoundException("Restaurant not found"));

        Item item = itemRepo.findById(itemid)
                .orElseThrow(() ->
                        new ItemNotFoundException("Item not found"));

        if(!restaurant.getMenuItems().contains(item)){
            throw new RuntimeException("Item not present in this restaurant menu");
        }

        restaurant.getMenuItems().remove(item);

        restaurantRepo.save(restaurant);

        ResponceStructure<String> rs = new ResponceStructure<>();

        rs.setStatusCode(HttpStatus.OK.value());
        rs.setMessage("Item removed from menu");
        rs.setData("Removed successfully");

        return new ResponseEntity<>(rs,HttpStatus.OK);
    }

}