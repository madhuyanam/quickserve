package com.alpha.quickserve.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alpha.quickserve.entity.Customer;
@Repository
public interface CustomerRepo extends JpaRepository<Customer,Integer> {
	 Customer findByMobno(long mobno);

	     void deleteByMobno(long mobno);
	    Optional<Customer> findByMobno(Long mobno);
}
