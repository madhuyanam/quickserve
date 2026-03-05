package com.alpha.quickserve.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.alpha.quickserve.dto.OrderNeedConsentDto;
import com.alpha.quickserve.entity.CartItem;
import com.alpha.quickserve.entity.Customer;
import com.alpha.quickserve.entity.Order;
import com.alpha.quickserve.entity.Restaurant;
import com.alpha.quickserve.responcestructure.ResponceStructure;
import com.alpha.quickserve.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // Register
    @PostMapping("/register")
    public ResponseEntity<ResponceStructure<Customer>> register(@RequestBody Customer customer){
        return customerService.register(customer);
    }

    // Find
    @GetMapping("/find")
    public ResponseEntity<ResponceStructure<Customer>> find(@RequestParam long mobno){
        return customerService.findCustomer(mobno);
    }

    // Delete
    @DeleteMapping("/delete")
    public ResponseEntity<ResponceStructure<String>> delete(@RequestParam long mobno){
        return customerService.deleteCustomer(mobno);
    }

    // Add To Cart
    @PostMapping("/addtocart")
    public ResponseEntity<ResponceStructure<String>> addToCart(
            @RequestParam long mobno,
            @RequestParam int itemid,
            @RequestParam int quantity){

        return customerService.addToCart(mobno,itemid,quantity);
    }

    // Get Cart
    @GetMapping("/getcart")
    public ResponseEntity<ResponceStructure<List<CartItem>>> getCart(@RequestParam long mobno){
        return customerService.getCart(mobno);
    }

    // Place Order
    @PostMapping("/placeorder")
    public ResponseEntity<ResponceStructure<OrderNeedConsentDto>> placeOrder(
            @RequestParam long mobno,
            @RequestParam String paymentType,
            @RequestParam String addressType,
            @RequestParam String specialRequest) {

        return customerService.placingOrder(mobno, paymentType, addressType, specialRequest);
    }

    // Confirm Order
    @PostMapping("/confirmorder")
    public ResponseEntity<ResponceStructure<String>> confirm(@RequestParam int orderid){
        return customerService.confirmPlacingOrder(orderid);
    }

    // Cancel Order
    @PostMapping("/cancelorder")
    public ResponseEntity<ResponceStructure<String>> cancel(@RequestParam int orderid){
        return customerService.denyPlacingOrder(orderid);
    }

    // Search Restaurant or Item
    @GetMapping("/searchitemorrestaurant")
    public ResponseEntity<ResponceStructure<List<Restaurant>>> searchItemOrRestaurant(
            @RequestParam long mobno,
            @RequestParam String searchkey){

        return customerService.searchItemOrRestaurant(mobno,searchkey);
    }
}