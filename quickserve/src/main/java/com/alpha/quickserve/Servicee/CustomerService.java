package com.alpha.quickserve.Servicee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alpha.quickserve.DTO.CustomerDto;
import com.alpha.quickserve.Exception.CustomerNotFoundException;
import com.alpha.quickserve.ResponceStructure.ResponceStructure;
import com.alpha.quickserve.entity.Customer;
import com.alpha.quickserve.repository.CustomerRepo;



@Service
public class CustomerService {

    @Autowired
    private CustomerRepo customerrepo;

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

   
}
