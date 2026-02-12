package com.alpha.quickserve.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alpha.quickserve.entity.Order;
@Repository
public interface OrderRepo extends JpaRepository<Order, Integer>{

}
