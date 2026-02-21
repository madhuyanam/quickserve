package com.alpha.quickserve.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alpha.quickserve.entity.Restaurant;


@Repository
public interface RestaurantRepo extends JpaRepository<Restaurant, Long>{
	
	Optional<Restaurant> findByMobno(long mobno);



}

