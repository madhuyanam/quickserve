package com.alpha.quickserve.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alpha.quickserve.config.RazorpayConfig;
import com.alpha.quickserve.dto.CartWithCouponsDto;
import com.alpha.quickserve.dto.CustomerDto;
import com.alpha.quickserve.dto.DistanceCalculation;
import com.alpha.quickserve.dto.OrderNeedConsentDto;
import com.alpha.quickserve.entity.CartItem;
import com.alpha.quickserve.entity.Coupon;
import com.alpha.quickserve.entity.CouponRedemption;
import com.alpha.quickserve.entity.Customer;
import com.alpha.quickserve.entity.DeliveryPartner;
import com.alpha.quickserve.entity.Item;
import com.alpha.quickserve.entity.Order;
import com.alpha.quickserve.entity.Payment;
import com.alpha.quickserve.entity.Restaurant;
import com.alpha.quickserve.exception.CartEmptyException;
import com.alpha.quickserve.exception.CodNotAllowedException;
import com.alpha.quickserve.exception.CouponExpiredException;
import com.alpha.quickserve.exception.CouponInvalidException;
import com.alpha.quickserve.exception.CouponLimitExceededException;
import com.alpha.quickserve.exception.CouponNotFoundException;
import com.alpha.quickserve.exception.CustomerNotFoundException;
import com.alpha.quickserve.exception.InvalidOrderStateException;
import com.alpha.quickserve.exception.ItemNotFoundException;
import com.alpha.quickserve.exception.OrderNotFoundException;
import com.alpha.quickserve.exception.PaymentFailedException;
import com.alpha.quickserve.exception.PaymentProcessingException;
import com.alpha.quickserve.exception.RestaurantNotFoundException;
import com.alpha.quickserve.repository.CouponRedemptionRepository;
import com.alpha.quickserve.repository.CouponRepository;
import com.alpha.quickserve.repository.CustomerRepository;
import com.alpha.quickserve.repository.ItemRepository;
import com.alpha.quickserve.repository.OrderRepository;
import com.alpha.quickserve.repository.RestaurantRepository;
import com.alpha.quickserve.responcestructure.ResponceStructure;
import com.razorpay.RazorpayClient;

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

    @Autowired
    private RazorpayService razorpayService;
    
    // Registering  Customer
    public ResponseEntity<ResponceStructure<Customer>> register(CustomerDto dto){

        Customer customer = new Customer();

        customer.setName(dto.getName());
        customer.setMobno(dto.getMobno());
        customer.setMailid(dto.getMailid());
        customer.setGender(dto.getGender());

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

        // COD restriction
        if(paymentType.equalsIgnoreCase("COD")
                && customer.getPenalty() > 0){

            throw new CodNotAllowedException(
                    "COD not allowed until penalty cleared");
        }

        Restaurant restaurant =
                customer.getCart().get(0).getItem().getRestaurant();

        double itemCost = 0;

        List<Item> items = new ArrayList<>();

        for(CartItem ci : customer.getCart()){

            itemCost += ci.getItem().getPrice() * ci.getQuantity();

            items.add(ci.getItem());
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
            deliveryCharges = (distance-2)*10;
        }

        double totalCost =
                itemCost + packagingFees + platformFees + tax + deliveryCharges;

        Order order = new Order();

        order.setCustomer(customer);
        order.setRestaurant(restaurant);

        order.setItems(items);

        order.setPickupaddress(
                restaurant.getAddress().getStreet());

        order.setDeliveryAddress(
                customer.getAddress().getStreet());

        order.setStatus("WAITING_FOR_CONSENT");

        order.setOriginalAmount(itemCost);

        order.setFinalAmount(totalCost);

        order.setCost(totalCost);

        order.setSpecialRequest(specialRequest);

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

        rs.setStatusCode(201);
        rs.setMessage("Order initiated. Waiting for consent");
        rs.setData(dto);

        return new ResponseEntity<>(rs,HttpStatus.CREATED);
    }
 // Confirm Order
    public ResponseEntity<ResponceStructure<String>> confirmOrderByCOD(int orderid){

        Order order = orderRepo.findById(orderid)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));

        if(!order.getStatus().equals("WAITING_FOR_CONSENT")){
            throw new InvalidOrderStateException("Order cannot be confirmed now");
        }

        // ensure payment type is COD
        if(order.getPayment()!=null &&
           !order.getPayment().getType().equalsIgnoreCase("COD")){
            throw new RuntimeException("Only COD orders require confirmation");
        }

        order.setStatus("ORDER_CONFIRMED_BY_CUSTOMER");

        orderRepo.save(order);

        ResponceStructure<String> rs = new ResponceStructure<>();

        rs.setStatusCode(HttpStatus.OK.value());
        rs.setMessage("COD Order Confirmed Successfully");
        rs.setData("ORDER_CONFIRMED_BY_CUSTOMER");

        return new ResponseEntity<>(rs,HttpStatus.OK);
    }
    
    //Confirm order By Online 
    public ResponseEntity<ResponceStructure<String>> confirmOrderByOnline(
            long mobno,
            int orderid){

        Customer customer = customerRepo.findByMobno(mobno)
                .orElseThrow(() ->
                        new CustomerNotFoundException("Customer not found"));

        Order order = orderRepo.findById(orderid)
                .orElseThrow(() ->
                        new OrderNotFoundException("Order not found"));

        if(!order.getStatus().equals("WAITING_FOR_CONSENT")){
            throw new InvalidOrderStateException("Order cannot be confirmed now");
        }

        double finalAmount = order.getFinalAmount();

        // add penalty
        if(customer.getPenalty() > 0){
            finalAmount = finalAmount + customer.getPenalty();
        }

        boolean paymentSuccess = razorpayService.makePayment(finalAmount);

        if(!paymentSuccess){

            order.setStatus("CANCELLED");

            // restore cart
            for(Item item : order.getItems()){

                CartItem cartItem = new CartItem();
                cartItem.setItem(item);
                cartItem.setQuantity(1);

                customer.getCart().add(cartItem);
            }

            orderRepo.save(order);
            customerRepo.save(customer);

            throw new PaymentFailedException("Payment failed. Order cancelled.");
        }

        // payment success

        Payment payment = new Payment();

        payment.setAmount(finalAmount);
        payment.setType("ONLINE");
        payment.setStatus("PAID");

        order.setPayment(payment);

        order.setStatus("ORDER_CONFIRMED_BY_CUSTOMER");

        // clear penalty after payment
        customer.setPenalty(0);

        orderRepo.save(order);
     
        customerRepo.save(customer);

        ResponceStructure<String> rs = new ResponceStructure<>();

        rs.setStatusCode(200);
        rs.setMessage("Payment Successful. Order Confirmed");
        rs.setData("ORDER_CONFIRMED_BY_CUSTOMER");

        return ResponseEntity.ok(rs);
    }
    
    //Distribution of payment
    private void distributePayment(Order order){

        double amount = order.getFinalAmount();

        Restaurant restaurant = order.getRestaurant();

        DeliveryPartner dp = order.getDeliveryPartner();

        double platformCommission = amount * 0.20;

        double restaurantShare = amount * 0.70;

        double deliveryPartnerShare = amount * 0.10;

        restaurant.setWallet(
                restaurant.getWallet() + restaurantShare
        );

        if(dp != null){

            dp.setRating(dp.getRating()); // optional update

        }

        restaurantRepo.save(restaurant);
    }
    
    //Restoring the cart item if the Payment fails
    private void restoreCart(Customer customer,Order order){

        List<Item> items = order.getItems();

        for(Item item : items){

            CartItem ci = new CartItem();

            ci.setItem(item);

            ci.setQuantity(1);

            customer.getCart().add(ci);
        }

        customerRepo.save(customer);
    }
    
    // Cancel Order
    public ResponseEntity<ResponceStructure<String>> cancelOrder(
            long mobno,
            int orderid){

        Customer customer = customerRepo.findByMobno(mobno)
                .orElseThrow(() ->
                        new CustomerNotFoundException("Customer not found"));

        Order order = orderRepo.findById(orderid)
                .orElseThrow(() ->
                        new OrderNotFoundException("Order not found"));

        if(order.getStatus().equals("ORDER_ON_THE_WAY")
                || order.getStatus().equals("ARRIVING")
                || order.getStatus().equals("AT_DOORSTEP")){

            double penalty = order.getFinalAmount()*0.5;

            customer.setPenalty(
                    customer.getPenalty()+penalty);
        }

        order.setStatus("CANCELLED");

        orderRepo.save(order);
        customerRepo.save(customer);

        ResponceStructure<String> rs = new ResponceStructure<>();

        rs.setStatusCode(200);
        rs.setMessage("Order Cancelled");
        rs.setData("CANCELLED");

        return ResponseEntity.ok(rs);
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