package com.alpha.quickserve.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alpha.quickserve.dto.OrderNeedConsentDto;
import com.alpha.quickserve.entity.*;
import com.alpha.quickserve.exception.*;
import com.alpha.quickserve.repository.*;
import com.alpha.quickserve.responcestructure.ResponceStructure;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private RestaurantRepository restaurantRepo;

    @Autowired
    private ItemRepository itemRepo;

    // Register Customer
    public ResponseEntity<ResponceStructure<Customer>> register(Customer customer){

        Customer savedCustomer = customerRepo.save(customer);

        ResponceStructure<Customer> rs = new ResponceStructure<>();
        rs.setStatusCode(HttpStatus.CREATED.value());
        rs.setMessage("Customer Registered Successfully");
        rs.setData(savedCustomer);

        return new ResponseEntity<>(rs,HttpStatus.CREATED);
    }

    // Find Customer
    public ResponseEntity<ResponceStructure<Customer>> findCustomer(long mobno){

        Customer customer = customerRepo.findByMobno(mobno)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

        ResponceStructure<Customer> rs = new ResponceStructure<>();
        rs.setStatusCode(HttpStatus.OK.value());
        rs.setMessage("Customer Found");
        rs.setData(customer);

        return new ResponseEntity<>(rs,HttpStatus.OK);
    }

    // Delete Customer
    public ResponseEntity<ResponceStructure<String>> deleteCustomer(long mobno){

        Customer customer = customerRepo.findByMobno(mobno)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

        customerRepo.delete(customer);

        ResponceStructure<String> rs = new ResponceStructure<>();
        rs.setStatusCode(HttpStatus.OK.value());
        rs.setMessage("Customer Deleted Successfully");
        rs.setData("Deleted");

        return new ResponseEntity<>(rs,HttpStatus.OK);
    }

    // Get Cart
    public ResponseEntity<ResponceStructure<List<CartItem>>> getCart(long mobno){

        Customer customer = customerRepo.findByMobno(mobno)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

        ResponceStructure<List<CartItem>> rs = new ResponceStructure<>();
        rs.setStatusCode(HttpStatus.OK.value());
        rs.setMessage("Cart Fetched Successfully");
        rs.setData(customer.getCart());

        return new ResponseEntity<>(rs,HttpStatus.OK);
    }

    // Place Order
    public ResponseEntity<ResponceStructure<OrderNeedConsentDto>> placingOrder(
            long mobno,
            String paymentType,
            String addressType,
            String specialRequest){

        Customer customer = customerRepo.findByMobno(mobno)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

        if(customer.getCart().isEmpty()){
            throw new RuntimeException("Cart is empty");
            // go with custome excetion
            //handle in geh
        }

        // Get restaurant from first cart item
        Restaurant restaurant =
                customer.getCart().get(0).getItem().getRestaurant();

        double itemCost = 0;

        for(CartItem ci : customer.getCart()){
            itemCost += ci.getItem().getPrice() * ci.getQuantity();
        }

        double packagingFees = restaurant.getPackagingFee();

        double platformFees = 5;

        double tax = itemCost * 0.05;
        
        // get the distance between customer and restaurant 
        // calculate the delivery charge .
        
        double deliveryCharges = 20;
        
        // get the above calcuated distance 
        double distance = 2.5;

        double totalCost =
                itemCost + packagingFees + platformFees + tax + deliveryCharges;

        Order order = new Order();

        order.setCustomer(customer);
        order.setRestaurant(restaurant);
        order.setSpecialRequest(specialRequest);
        order.setStatus("WAITING_FOR_CONSENT");
        order.setCost((int) totalCost);

        Order savedOrder = orderRepo.save(order);

        OrderNeedConsentDto dto = new OrderNeedConsentDto();

        dto.setOrderId(savedOrder.getId());
        dto.setRestaurantName(restaurant.getName());
        dto.setItemCost(itemCost);
        dto.setPackagingFees(packagingFees);
        dto.setPlatformFees(platformFees);
        dto.setTax(tax);
        dto.setDeliveryCharges(deliveryCharges);
        dto.setDistance(distance);
        dto.setTotalCost(totalCost);

        ResponceStructure<OrderNeedConsentDto> rs =
                new ResponceStructure<>();

        rs.setStatusCode(HttpStatus.CREATED.value());
        rs.setMessage("Order created - waiting for customer consent");
        rs.setData(dto);

        return new ResponseEntity<>(rs, HttpStatus.CREATED);
    }

    // Confirm Order
    public ResponseEntity<ResponceStructure<String>> confirmPlacingOrder(int orderid){

        Order order = orderRepo.findById(orderid)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));
        Restaurant rest = order.getItem().get(0).getRestaurant();
        order.setRestaurant(rest);
        rest.getOrders().add(order);
        order.setStatus("PLACED");
        // save order also
        orderRepo.save(order);

        ResponceStructure<String> rs = new ResponceStructure<>();
        rs.setStatusCode(HttpStatus.OK.value());
        rs.setMessage("Order Confirmed");
        rs.setData("Success");

        return new ResponseEntity<>(rs,HttpStatus.OK);
    }

    // Cancel Order
    public ResponseEntity<ResponceStructure<String>> denyPlacingOrder(int orderid){

        Order order = orderRepo.findById(orderid)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));

        order.setStatus("CANCELLED");
        orderRepo.save(order);

        ResponceStructure<String> rs = new ResponceStructure<>();
        rs.setStatusCode(HttpStatus.OK.value());
        rs.setMessage("Order Cancelled");
        rs.setData("Cancelled");

        return new ResponseEntity<>(rs,HttpStatus.OK);
    }

    // Search Restaurant or Item
    public ResponseEntity<ResponceStructure<List<Restaurant>>> searchItemOrRestaurant(long mobno,String searchkey){

        Customer customer = customerRepo.findByMobno(mobno)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

        String city = customer.getAddress().getCity();

        List<Restaurant> restaurants = restaurantRepo.findByAddress_City(city);

        List<Restaurant> result = restaurants.stream()
                .filter(r ->
                        r.getName().toLowerCase().contains(searchkey.toLowerCase())
                        ||
                        r.getMenuItems().stream()
                                .anyMatch(i -> i.getName().toLowerCase().contains(searchkey.toLowerCase()))
                )
                .toList();

        ResponceStructure<List<Restaurant>> rs = new ResponceStructure<>();
        rs.setStatusCode(HttpStatus.OK.value());
        rs.setMessage("Search Results");
        rs.setData(result);

        return new ResponseEntity<>(rs,HttpStatus.OK);
    }

    // Add To Cart
    public ResponseEntity<ResponceStructure<String>> addToCart(long mobno,int itemid,int quantity){

        Customer customer = customerRepo.findByMobno(mobno)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

        Item item = itemRepo.findById(itemid)
                .orElseThrow(() -> new ItemNotFoundException("Item not found"));

        CartItem cartItem = new CartItem();
        cartItem.setItem(item);
        cartItem.setQuantity(quantity);

        customer.getCart().add(cartItem);

        customerRepo.save(customer);

        ResponceStructure<String> rs = new ResponceStructure<>();
        rs.setStatusCode(HttpStatus.OK.value());
        rs.setMessage("Item Added To Cart");
        rs.setData("Success");

        return new ResponseEntity<>(rs,HttpStatus.OK);
    }
}