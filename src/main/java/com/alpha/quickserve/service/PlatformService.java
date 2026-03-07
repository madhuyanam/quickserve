package com.alpha.quickserve.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alpha.quickserve.entity.Coupon;
import com.alpha.quickserve.exception.CouponInvalidException;
import com.alpha.quickserve.exception.CouponNotFoundException;
import com.alpha.quickserve.repository.CouponRedemptionRepository;
import com.alpha.quickserve.repository.CouponRepository;
import com.alpha.quickserve.responcestructure.ResponceStructure;

@Service
public class PlatformService {

    @Autowired
    private CouponRepository couponRepo;
<<<<<<< HEAD
    
    //Creating Coupon
=======

    @Autowired
    private CouponRedemptionRepository couponRedemptionRepo;

    // CREATE COUPON
>>>>>>> 918e3e7547ec73928df7b0c5332fd6e36991a8f2
    public ResponseEntity<ResponceStructure<Coupon>> createCoupon(Coupon coupon){

        Coupon savedCoupon = couponRepo.save(coupon);

        ResponceStructure<Coupon> rs = new ResponceStructure<>();
        rs.setStatusCode(HttpStatus.CREATED.value());
        rs.setMessage("Coupon Created Successfully");
        rs.setData(savedCoupon);

        return new ResponseEntity<>(rs,HttpStatus.CREATED);
    }
<<<<<<< HEAD
    
    //Deleting Coupon 
    public ResponseEntity<ResponceStructure<String>> deleteCoupon(Integer id){
=======
>>>>>>> 918e3e7547ec73928df7b0c5332fd6e36991a8f2


    // DELETE COUPON
    public ResponseEntity<ResponceStructure<String>> deleteCoupon(Integer couponId){

        Coupon coupon = couponRepo.findById(couponId)
                .orElseThrow(() -> new CouponNotFoundException("Coupon not found"));

        // Check if coupon already used
        if(couponRedemptionRepo.existsByCoupon(coupon)){
            throw new CouponInvalidException("Coupon already used by customers, cannot delete");
        }

        couponRepo.delete(coupon);

        ResponceStructure<String> rs = new ResponceStructure<>();
        rs.setStatusCode(HttpStatus.OK.value());
        rs.setMessage("Coupon Deleted Successfully");
        rs.setData("Deleted");

        return new ResponseEntity<>(rs,HttpStatus.OK);
    }
<<<<<<< HEAD
    
    
    //Updating Coupon
=======


    // UPDATE COUPON
>>>>>>> 918e3e7547ec73928df7b0c5332fd6e36991a8f2
    public ResponseEntity<ResponceStructure<Coupon>> updateCoupon(
            Integer couponId,
            String expiryDate){

        Coupon coupon = couponRepo.findById(couponId)
                .orElseThrow(() -> new CouponNotFoundException("Coupon not found"));

        boolean used = couponRedemptionRepo.existsByCoupon(coupon);

        // If nobody used coupon → extend expiry
        if(!used){
            coupon.setExpiryDate(LocalDate.parse(expiryDate));
        }
        else{
            // If used → reduce maxCoupons
            if(coupon.getMaxCoupons() > 0){
                coupon.setMaxCoupons(coupon.getMaxCoupons() - 1);
            }
        }

        couponRepo.save(coupon);

        ResponceStructure<Coupon> rs = new ResponceStructure<>();
        rs.setStatusCode(HttpStatus.OK.value());
        rs.setMessage("Coupon Updated Successfully");
        rs.setData(coupon);

        return new ResponseEntity<>(rs,HttpStatus.OK);
    }
<<<<<<< HEAD
    
    //Finding the Coupon
=======


    // FIND COUPON
>>>>>>> 918e3e7547ec73928df7b0c5332fd6e36991a8f2
    public ResponseEntity<ResponceStructure<Coupon>> findCoupon(Integer couponId){

        Coupon coupon = couponRepo.findById(couponId)
                .orElseThrow(() -> new CouponNotFoundException("Coupon not found"));

        ResponceStructure<Coupon> rs = new ResponceStructure<>();
        rs.setStatusCode(HttpStatus.OK.value());
        rs.setMessage("Coupon Fetched Successfully");
        rs.setData(coupon);

        return new ResponseEntity<>(rs,HttpStatus.OK);
    }

}
