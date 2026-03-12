package com.alpha.quickserve.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.alpha.quickserve.dto.CartWithCouponsDto;
import com.alpha.quickserve.dto.CustomerDto;
import com.alpha.quickserve.dto.OrderNeedConsentDto;
import com.alpha.quickserve.entity.Customer;
import com.alpha.quickserve.entity.Restaurant;
import com.alpha.quickserve.responcestructure.ResponceStructure;
import com.alpha.quickserve.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // Registering the customer
    @PostMapping("/register")
    public ResponseEntity<ResponceStructure<Customer>> register(@RequestBody CustomerDto cdto){

    	return customerService.register(cdto);
    }

    // Finding the customer by mob no
    @GetMapping("/find")
    public ResponseEntity<ResponceStructure<Customer>> find(@RequestParam long mobno){
    	
        return customerService.findCustomer(mobno);
    }

    // Deleting the customer by mob no
    @DeleteMapping("/delete")
    public ResponseEntity<ResponceStructure<String>> delete(@RequestParam long mobno){
    	
        return customerService.deleteCustomer(mobno);
    }

    // Adding item to the cart by mob no,item id,quantity
    @PostMapping("/addToCart")
    public ResponseEntity<ResponceStructure<String>> addToCart(
    		@RequestParam long mobno,
    		@RequestParam int itemid,
    		@RequestParam int quantity){

        return customerService.addToCart(mobno,itemid,quantity);
    }

    // Getting the cart
    @GetMapping("/getCart")
    public ResponseEntity<ResponceStructure<CartWithCouponsDto>> getCart(@RequestParam long mobno){
    	
        return customerService.getCart(mobno);
    }

    // Placing the Order
    @PostMapping("/placeOrder")
    public ResponseEntity<ResponceStructure<OrderNeedConsentDto>> placeOrder(
    		@RequestParam long mobno,
    		@RequestParam String paymentType,
    		@RequestParam String addressType,
    		@RequestParam String specialRequest,
            @RequestParam Integer couponId) {

        return customerService.placeOrder(mobno, paymentType, addressType, specialRequest, couponId);
    }

    // Confirm Order
    @PostMapping("/confirmOrderByCOD")
    public ResponseEntity<ResponceStructure<String>> confirmOrderByCOD(
            @RequestParam int orderid){

        return customerService.confirmOrderByCOD(orderid);
    }

    // Cancel Order
    @PostMapping("/cancelorder")
    public ResponseEntity<ResponceStructure<String>> cancelOrder(
            @RequestParam long mobno,
            @RequestParam int orderid){

        return customerService.cancelOrder(mobno, orderid);
    }

    // Search Restaurant or Item
    @GetMapping("/searchitemorrestaurant")
    public ResponseEntity<ResponceStructure<List<Restaurant>>> searchItemOrRestaurant(
            @RequestParam long mobno,
            @RequestParam String searchkey){

        return customerService.searchItemOrRestaurant(mobno,searchkey);
    }
    
    //Removing item from the cart
    @DeleteMapping("/removeitemfromcart")
    public ResponseEntity<ResponceStructure<String>> removeItemFromCart(
            @RequestParam long customermobno,
            @RequestParam long restmob,
            @RequestParam int itemid){

        return customerService.removeItemFromCart(customermobno, restmob, itemid);
    }
    
    //confirm paying orderbyonline
    @PostMapping("/confirmorderByonline")
    public ResponseEntity<ResponceStructure<String>> confirmPayingOrderByOnline(
            @RequestParam long customermobno,
            @RequestParam int orderid){

        return customerService.confirmOrderByOnline(customermobno,orderid);
    }
}