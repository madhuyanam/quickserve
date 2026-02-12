package com.alpha.quickserve.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alpha.quickserve.entity.Vehicle;



@Repository
public interface VehicleRepo extends JpaRepository<Vehicle, Integer> {

}
