package com.alpha.quickserve.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alpha.quickserve.entity.Restaurant;


@Repository
public interface RestaurantRepo extends JpaRepository<Restaurant, Integer>{
<<<<<<< HEAD

	Optional<Restaurant> findByMobno(long mobno);

=======
	
	Optional<Restaurant> findByMobno(long mobno);
	
	 void deleteByMobno(long mobno);
	
>>>>>>> cda2d98be7f7ad05e9264d509f014761d1e0be07
	List<Restaurant> findByAddress_City(String city);


}

