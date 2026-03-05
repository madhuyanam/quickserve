package com.alpha.quickserve.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.alpha.quickserve.entity.Item;
import com.alpha.quickserve.entity.Restaurant;
import com.alpha.quickserve.responcestructure.ResponceStructure;
import com.alpha.quickserve.service.RestaurantService;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    // Register
    @PostMapping("/register")
    public ResponseEntity<ResponceStructure<Restaurant>> register(@RequestBody Restaurant restaurant){
        return restaurantService.register(restaurant);
    }

    // Find
    @GetMapping("/find")
    public ResponseEntity<ResponceStructure<Restaurant>> find(@RequestParam long mobno){
        return restaurantService.findRestaurant(mobno);
    }

    // Delete
    @DeleteMapping("/delete")
    public ResponseEntity<ResponceStructure<String>> delete(@RequestParam long mobno){
        return restaurantService.deleteRestaurant(mobno);
    }

    // Add Item To Menu
    @PostMapping("/additemtomenu")
    public ResponseEntity<ResponceStructure<Restaurant>> addItem(
            @RequestParam long mobno,
            @RequestBody Item item){

        return restaurantService.addItemToMenu(mobno,item);
    }

    // Update Status
    @PatchMapping("/updatestatus")
    public ResponseEntity<ResponceStructure<String>> updateStatus(
            @RequestParam long mobno,
            @RequestParam String status){

        return restaurantService.updateStatus(mobno,status);
    }

    // Update Item Availability
    @PatchMapping("/updateitemavailability")
    public ResponseEntity<ResponceStructure<String>> updateItemAvailability(
            @RequestParam int itemid,
            @RequestParam String availability){

        return restaurantService.updateItemAvailability(itemid,availability);
    }

    // Find Nearby Delivery Partners
    @GetMapping("/findnearbypartners")
    public ResponseEntity<ResponceStructure<List<String>>> findNearbyPartners(
            @RequestParam double latitude,
            @RequestParam double longitude){

        return restaurantService.findNearbyDeliveryPartners(latitude,longitude);
    }

    // Accept Order
    @PostMapping("/acceptorder")
    public ResponseEntity<ResponceStructure<List<String>>> acceptOrder(
            @RequestParam double latitude,
            @RequestParam double longitude,
            @RequestParam Integer orderid){

        return restaurantService.acceptOrder(latitude,longitude,orderid);
    }

}