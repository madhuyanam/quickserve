package com.alpha.quickserve.repository;



<<<<<<< HEAD

=======
>>>>>>> cda2d98be7f7ad05e9264d509f014761d1e0be07
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alpha.quickserve.entity.Customer;
@Repository
public interface CustomerRepo extends JpaRepository<Customer,Integer> {
	Optional<Customer> findByMobno(long mobno);

<<<<<<< HEAD
	void deleteByMobno(Long mobno);

=======
	     void deleteByMobno(long mobno);
	    Optional<Customer> findByMobno(Long mobno);
>>>>>>> cda2d98be7f7ad05e9264d509f014761d1e0be07
}
