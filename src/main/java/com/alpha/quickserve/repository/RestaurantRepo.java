package com.alpha.quickserve.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alpha.quickserve.entity.Restaurant;


@Repository
public interface RestaurantRepo extends JpaRepository<Restaurant, Long>{
	
Restaurant findByMobno(long mobno);
	
	void deleteByMobno(long mobno);
	
	Optional<Restaurant> findByMobno(Long mobno);

//public Optional<List<Restaurant>> FindByAddress_city(String cityname);

}

