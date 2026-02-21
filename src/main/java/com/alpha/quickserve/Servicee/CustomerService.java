package com.alpha.quickserve.Servicee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alpha.quickserve.DTO.CustomerDto;
import com.alpha.quickserve.Exception.CustomerNotFoundException;
import com.alpha.quickserve.ResponceStructure.ResponceStructure;
import com.alpha.quickserve.entity.Customer;
import com.alpha.quickserve.entity.Restaurant;
import com.alpha.quickserve.repository.CustomerRepo;
import com.alpha.quickserve.repository.RestaurantRepo;

import jakarta.transaction.Transactional;



@Service
public class CustomerService {

    @Autowired
    private CustomerRepo customerrepo;
    
    @Autowired
    private RestaurantRepo restaurantrepo;

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

            Customer customer = customerrepo.findByMobno(mobno);

            if (customer == null) {
                throw new CustomerNotFoundException("Customer not found with mobile number: " + mobno);
            }

            ResponceStructure<Customer> response = new ResponceStructure<>();
            response.setStatusCode(HttpStatus.FOUND.value());
            response.setMessage("Customer Found Successfully");
            response.setData(customer);

            return new ResponseEntity<>(response, HttpStatus.FOUND);
        }
        
        @Transactional
        public ResponseEntity<ResponceStructure<String>> deleteByMobno(long mobno) {

            Customer customer = customerrepo.findByMobno(mobno);

            if (customer == null) {
                throw new CustomerNotFoundException("Customer not found with mobile number: " + mobno);
            }

            customerrepo.deleteByMobno(mobno);
            ResponceStructure<String> response = new ResponceStructure<>();
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Customer Deleted Successfully");
            response.setData("Deleted customer with mobile number: " + mobno);

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        
        
        
        
        
        
public ResponseEntity<ResponceStructure<List<Restaurant>>> searchItemOrRestaurant(long custmob, String searchkey) {

            
        	Customer customer = customerrepo.findByMobno(custmob);

        	if (customer == null) {
        	    throw new CustomerNotFoundException(
        	            "Customer with mobile " + custmob + " not found");
        	}
          
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

            return new ResponseEntity<>(rs, HttpStatus.OK);
        }



        


   
}
