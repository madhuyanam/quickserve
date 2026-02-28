package com.alpha.quickserve.Servicee;

<<<<<<< HEAD
=======
import java.util.ArrayList;
>>>>>>> cda2d98be7f7ad05e9264d509f014761d1e0be07
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alpha.quickserve.DTO.CustomerDto;
import com.alpha.quickserve.Exception.CustomerNotFoundException;
import com.alpha.quickserve.ResponceStructure.ResponceStructure;
<<<<<<< HEAD
=======
import com.alpha.quickserve.entity.Address;
import com.alpha.quickserve.entity.CartItem;
>>>>>>> cda2d98be7f7ad05e9264d509f014761d1e0be07
import com.alpha.quickserve.entity.Customer;
import com.alpha.quickserve.entity.Item;
import com.alpha.quickserve.entity.Order;
import com.alpha.quickserve.entity.Restaurant;
import com.alpha.quickserve.repository.CustomerRepo;
import com.alpha.quickserve.repository.ItemRepo;
import com.alpha.quickserve.repository.OrderRepo;
import com.alpha.quickserve.repository.RestaurantRepo;

<<<<<<< HEAD
=======
import jakarta.transaction.Transactional;

>>>>>>> cda2d98be7f7ad05e9264d509f014761d1e0be07
@Service
@Transactional
public class CustomerService {

    @Autowired
    private CustomerRepo customerrepo;

    @Autowired
    private RestaurantRepo restaurantrepo;
    
    @Autowired
    private ItemRepo itemrepo;
    
    @Autowired
    private OrderRepo orderRepo;

    public ResponseEntity<ResponceStructure<Customer>> saveCustomer(CustomerDto cdto) {

        Customer customer = new Customer();
        customer.setName(cdto.getName());
        customer.setMobno(cdto.getMobno());
        customer.setMailid(cdto.getMailid());
        customer.setGender(cdto.getGender());

        Customer saved = customerrepo.save(customer);

        ResponceStructure<Customer> response = new ResponceStructure<>();
        response.setStatusCode(HttpStatus.CREATED.value());
        response.setMessage("Customer Saved Successfully");
        response.setData(saved);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    public ResponseEntity<ResponceStructure<Customer>> findByMobno(long mobno) {

    	Customer customer = customerrepo.findByMobno(mobno).orElseThrow(() ->new CustomerNotFoundException("Customer not found with mobile number: " + mobno));

<<<<<<< HEAD
        ResponceStructure<Customer> response = new ResponceStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Customer Found Successfully");
        response.setData(customer);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
=======
            ResponceStructure<Customer> response = new ResponceStructure<>();
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Customer Found Successfully");
            response.setData(customer);

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        
     
        public ResponseEntity<ResponceStructure<String>> deleteByMobno(long mobno) {
>>>>>>> cda2d98be7f7ad05e9264d509f014761d1e0be07

    public ResponseEntity<ResponceStructure<String>> deleteByMobno(long mobno) {

        Customer customer = customerrepo
                .findByMobno(mobno)
                .orElseThrow(() ->
                        new CustomerNotFoundException(
                                "Customer not found with mobile number: " + mobno));

        customerrepo.delete(customer);

<<<<<<< HEAD
        ResponceStructure<String> response = new ResponceStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Customer Deleted Successfully");
        response.setData("Deleted customer with mobile number: " + mobno);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<ResponceStructure<List<Restaurant>>> searchItemOrRestaurant(
            long mobno, String searchkey) {
=======
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        
        
        
        
        
        
public ResponseEntity<ResponceStructure<List<Restaurant>>> searchItemOrRestaurant(long mobno, String searchkey) {

            
        	Customer customer = customerrepo.findByMobno(mobno);

        	if (customer == null) {
        	    throw new CustomerNotFoundException(
        	            "Customer with mobile " + mobno + " not found");
        	}
          
            String city = customer.getAddress().getCity();
>>>>>>> cda2d98be7f7ad05e9264d509f014761d1e0be07

        Customer customer = customerrepo
                .findByMobno(mobno)
                .orElseThrow(() ->
                        new CustomerNotFoundException("Customer not found"));

        String city = customer.getAddress().getCity();

        List<Restaurant> restaurantsInCity =
                restaurantrepo.findByAddress_City(city);

        List<Restaurant> filteredRestaurants = restaurantsInCity.stream()
                .filter(r ->
                        r.getName().toLowerCase().contains(searchkey.toLowerCase())
                                ||
                                r.getMenuItems().stream()
                                        .anyMatch(i ->
                                                i.getName().toLowerCase()
                                                        .contains(searchkey.toLowerCase())
                                        )
                )
                .toList();

        ResponceStructure<List<Restaurant>> rs = new ResponceStructure<>();
        rs.setStatusCode(HttpStatus.OK.value());
        rs.setMessage("Search completed successfully");
        rs.setData(filteredRestaurants);

<<<<<<< HEAD
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }
}
=======
         
       public ResponseEntity<ResponceStructure<String>> addToCart(
        long mobno, int itemId, int quantity) {

    Customer customer = customerrepo.findByMobno(mobno);

    if (customer == null) {
        throw new CustomerNotFoundException(
                "Customer not found with mobile: " + mobno);
    }

    Item item = itemrepo.findById(itemId)
    		.orElseThrow(() -> new RuntimeException(
    		        "Item not found with id: " + itemId));

    List<CartItem> cart = customer.getCart();

    if (cart == null) {
        cart = new ArrayList<>();
        customer.setCart(cart);
    }

    // If cart not empty → check restaurant
    if (!cart.isEmpty()) {

        int existingRestaurantId =
                cart.get(0).getItem().getRestaurant().getId();

        int newRestaurantId =
                item.getRestaurant().getId();

        if (existingRestaurantId != newRestaurantId) {
            cart.clear();                       // clear old cart
        }
    }

    //  Check if item already exists
    for (CartItem ci : cart) {
        if (ci.getItem().getId() == itemId) {

            ci.setQuantity(ci.getQuantity() + quantity);

            customerrepo.save(customer);

            ResponceStructure<String> rs =
                    new ResponceStructure<>();
            rs.setStatusCode(HttpStatus.OK.value());
            rs.setMessage("Quantity Updated");
            rs.setData("Item quantity increased");

            return new ResponseEntity<>(rs, HttpStatus.OK);
        }
    }

    //  Add new cart item
    CartItem newCart = new CartItem();
    newCart.setItem(item);
    newCart.setQuantity(quantity);

    cart.add(newCart);

    customerrepo.save(customer);

    ResponceStructure<String> rs =
            new ResponceStructure<>();
    rs.setStatusCode(HttpStatus.OK.value());
    rs.setMessage("Item Added To Cart");
    rs.setData("Added successfully");

    return new ResponseEntity<>(rs, HttpStatus.OK);
}
       
       
       
       
      
  

   
}
>>>>>>> cda2d98be7f7ad05e9264d509f014761d1e0be07
