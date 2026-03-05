package com.alpha.quickserve.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alpha.quickserve.entity.Order;

public interface OrderRepository extends JpaRepository<Order,Integer>{

}
