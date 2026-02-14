package com.alpha.quickserve.Controller1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.quickserve.DTO.CustomerDto;
import com.alpha.quickserve.ResponceStructure.ResponceStructure;
import com.alpha.quickserve.Servicee.CustomerService;
import com.alpha.quickserve.entity.Customer;

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
}
