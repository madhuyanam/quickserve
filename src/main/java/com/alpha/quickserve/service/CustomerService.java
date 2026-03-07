package com.alpha.quickserve.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alpha.quickserve.dto.CartWithCouponsDto;
import com.alpha.quickserve.dto.DistanceCalculation;
import com.alpha.quickserve.dto.OrderNeedConsentDto;
import com.alpha.quickserve.entity.CartItem;
import com.alpha.quickserve.entity.Coupon;
import com.alpha.quickserve.entity.CouponRedemption;
import com.alpha.quickserve.entity.Customer;
import com.alpha.quickserve.entity.Item;
import com.alpha.quickserve.entity.Order;
import com.alpha.quickserve.entity.Restaurant;
import com.alpha.quickserve.exception.CartEmptyException;
import com.alpha.quickserve.exception.CouponExpiredException;
import com.alpha.quickserve.exception.CouponInvalidException;
import com.alpha.quickserve.exception.CouponLimitExceededException;
import com.alpha.quickserve.exception.CouponNotFoundException;
import com.alpha.quickserve.exception.CustomerNotFoundException;
import com.alpha.quickserve.exception.ItemNotFoundException;
import com.alpha.quickserve.exception.OrderNotFoundException;
import com.alpha.quickserve.exception.RestaurantNotFoundException;
import com.alpha.quickserve.repository.CouponRedemptionRepository;
import com.alpha.quickserve.repository.CouponRepository;
import com.alpha.quickserve.repository.CustomerRepository;
import com.alpha.quickserve.repository.ItemRepository;
import com.alpha.quickserve.repository.OrderRepository;
import com.alpha.quickserve.repository.RestaurantRepository;
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
    
    @Autowired
    private CouponRepository couponRepo;
    
    @Autowired
    private CouponRedemptionRepository couponRedemptionRepo;

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

  //get cart
    public ResponseEntity<ResponceStructure<CartWithCouponsDto>> getCart(long mobno){

        Customer customer = customerRepo.findByMobno(mobno)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

        List<CartItem> cart = customer.getCart();

        double total = 0;

        for(CartItem c : cart){
            total += c.getItem().getPrice() * c.getQuantity();
        }

        List<Coupon> allCoupons = couponRepo.findByStatus("ACTIVE");

        List<Coupon> coupons = new ArrayList<>();

        for(Coupon c : allCoupons){

            boolean used = couponRedemptionRepo
                    .findByCouponAndCustomer(c, customer)
                    .isPresent();

            if(!used &&
               !c.getExpiryDate().isBefore(LocalDate.now()) &&
               c.getMaxCoupons() > 0 &&
               total >= c.getMinOrderPrice()){

                coupons.add(c);
            }
        }

        CartWithCouponsDto dto = new CartWithCouponsDto();

        dto.setCartItems(cart);
        dto.setCartTotal(total);
        dto.setCoupons(coupons);

        ResponceStructure<CartWithCouponsDto> rs = new ResponceStructure<>();

        rs.setStatusCode(HttpStatus.OK.value());
        rs.setMessage("Cart fetched successfully");
        rs.setData(dto);

        return new ResponseEntity<>(rs,HttpStatus.OK);
    }



 // Place Order
    public ResponseEntity<ResponceStructure<OrderNeedConsentDto>> placeOrder(
            long mobno,
            String paymentType,
            String addressType,
            String specialRequest,
            Integer couponId){

        Customer customer = customerRepo.findByMobno(mobno)
                .orElseThrow(() ->
                        new CustomerNotFoundException("Customer not found"));

        if(customer.getCart().isEmpty()){
            throw new CartEmptyException("Cart is empty");
        }

        Restaurant restaurant =
                customer.getCart().get(0).getItem().getRestaurant();

        double itemCost = 0;

        for(CartItem ci : customer.getCart()){
            itemCost += ci.getItem().getPrice() * ci.getQuantity();
        }

        double packagingFees = restaurant.getPackagingFee();

        double platformFees = 5;

        double tax = itemCost * 0.05;

        double distance = DistanceCalculation.calculateDistance(
                restaurant.getAddress().getLatitude(),
                restaurant.getAddress().getLongitude(),
                customer.getAddress().getLatitude(),
                customer.getAddress().getLongitude()
        );

        double deliveryCharges = 0;

        if(distance > 2){
            deliveryCharges = (distance - 2) * 10;
        }

        double totalCost =
                itemCost + packagingFees + platformFees + tax + deliveryCharges;

        double discount = 0;

        Coupon coupon = null;

        // Apply coupon if provided
        if(couponId != null){

            coupon = couponRepo.findById(couponId)
                    .orElseThrow(() -> new CouponNotFoundException("Coupon not found"));

            if(LocalDate.now().isAfter(coupon.getExpiryDate())){
                throw new CouponExpiredException("Coupon expired");
            }

            if(totalCost < coupon.getMinOrderPrice()){
                throw new CouponInvalidException("Minimum order price not satisfied");
            }

            if(coupon.getMaxCoupons() <= 0){
                throw new CouponLimitExceededException("Coupon limit reached");
            }

            Optional<CouponRedemption> redemption =
                    couponRedemptionRepo.findByCouponAndCustomer(coupon,customer);

            if(redemption.isPresent()){
                throw new CouponInvalidException("Coupon already used");
            }

            discount = totalCost * coupon.getOffer() / 100;

            if(discount > coupon.getMaxRedeemPrice()){
                discount = coupon.getMaxRedeemPrice();
            }

            totalCost = totalCost - discount;
        }

        Order order = new Order();

        order.setCustomer(customer);
        order.setRestaurant(restaurant);
        order.setSpecialRequest(specialRequest);
        order.setStatus("WAITING_FOR_CONSENT");

        order.setOriginalAmount(itemCost);
        order.setDiscountAmount(discount);
        order.setFinalAmount(totalCost);

        order.setCoupon(coupon);

        order.setCost((int) totalCost);

        Order savedOrder = orderRepo.save(order);

        // update coupon usage
        if(coupon != null){

            coupon.setMaxCoupons(coupon.getMaxCoupons() - 1);
            couponRepo.save(coupon);

            CouponRedemption cr = new CouponRedemption();

            cr.setCoupon(coupon);
            cr.setCustomer(customer);
            cr.setOrder(savedOrder);

            couponRedemptionRepo.save(cr);
        }

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

        return new ResponseEntity<>(rs,HttpStatus.CREATED);
    }

 // Confirm Order
    public ResponseEntity<ResponceStructure<String>> confirmPlacingOrder(int orderid){

        Order order = orderRepo.findById(orderid)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));

        // Order status change
        order.setStatus("PLACED");

        orderRepo.save(order);

        ResponceStructure<String> rs = new ResponceStructure<>();

        rs.setStatusCode(HttpStatus.OK.value());
        rs.setMessage("Order Confirmed Successfully");
        rs.setData("Order placed successfully");

        return new ResponseEntity<>(rs, HttpStatus.OK);
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
    
    //Remove item from cart
    public ResponseEntity<ResponceStructure<String>> removeItemFromCart(long customermobno,long restmob,int itemid){

        Customer customer = customerRepo.findByMobno(customermobno).orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

        if(customer.getCart() == null || customer.getCart().isEmpty()){
            throw new CartEmptyException("Cart is empty");
        }

        Restaurant restaurant = restaurantRepo.findByMobno(restmob)
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found"));

        CartItem cartItem = customer.getCart().stream()
                .filter(ci -> ci.getItem().getId() == itemid
                && ci.getItem().getRestaurant().getMobno() == restmob)
                .findFirst()
                .orElseThrow(() -> new ItemNotFoundException("Item not found in cart"));

        customer.getCart().remove(cartItem);
        customerRepo.save(customer);

        ResponceStructure<String> rs = new ResponceStructure<>();
        rs.setStatusCode(HttpStatus.OK.value());
        rs.setMessage("Item removed from cart successfully");
        rs.setData("Removed Item ID: " + itemid);

        return new ResponseEntity<>(rs,HttpStatus.OK);
    }
}