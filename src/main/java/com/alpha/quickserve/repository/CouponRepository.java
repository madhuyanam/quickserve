package com.alpha.quickserve.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alpha.quickserve.entity.Coupon;


@Repository
public interface CouponRepository extends JpaRepository<Coupon,Integer> {

    List<Coupon> findByStatus(String status);

}
