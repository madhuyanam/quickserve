package com.alpha.quickserve.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alpha.quickserve.entity.DeliveryPartner;
@Repository
public interface DelivaryPartnerRepository extends JpaRepository<DeliveryPartner, Integer> {

	Optional<DeliveryPartner> findByMob(long mob);
}
