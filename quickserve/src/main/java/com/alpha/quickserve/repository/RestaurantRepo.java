package com.alpha.quickserve.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jakarta.annotation.Resource;

@Repository
public interface RestaurantRepo extends JpaRepository<Resource, Integer>{

}
