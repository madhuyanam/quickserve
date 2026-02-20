package com.alpha.quickserve.Controller1;

import java.security.Provider.Service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.quickserve.DTO.CustomerDto;
import com.alpha.quickserve.ResponceStructure.ResponceStructure;
import com.alpha.quickserve.Servicee.CustomerService;
import com.alpha.quickserve.entity.Customer;
import com.alpha.quickserve.entity.Restaurant;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerservice;

    @PostMapping("/register")
    public ResponseEntity<ResponceStructure<Customer>> saveCustomer(
            @RequestBody CustomerDto cdto) {

        return customerservice.saveCustomer(cdto);
    }
        @GetMapping("/find/{mobno}")
        public ResponseEntity<ResponceStructure<Customer>> find(@PathVariable long mobno) {
            return customerservice.findByMobno(mobno);
        }

        @DeleteMapping("/delete/{mobno}")
        public ResponseEntity<ResponceStructure<String>> delete(@PathVariable long mobno) {
            return customerservice.deleteByMobno(mobno);
        }
        
        
        
        
        @GetMapping("/searchitemorrestaurant")
        public ResponseEntity<ResponceStructure<List<Restaurant>>> 
        searchItemOrRestaurant(
                @RequestParam long custmob,
                @RequestParam String searchkey) {

            return customerservice.searchItemOrRestaurant(custmob, searchkey);
        }

    
    }

