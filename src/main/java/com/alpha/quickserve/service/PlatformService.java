package com.alpha.quickserve.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alpha.quickserve.entity.Coupon;
import com.alpha.quickserve.repository.CouponRepository;
import com.alpha.quickserve.responcestructure.ResponceStructure;

@Service
public class PlatformService {
    @Autowired
    private CouponRepository couponRepo;
    
    //Creating Coupon
    public ResponseEntity<ResponceStructure<Coupon>> createCoupon(Coupon coupon){

        Coupon saved = couponRepo.save(coupon);

        ResponceStructure<Coupon> rs = new ResponceStructure<>();
        rs.setStatusCode(HttpStatus.CREATED.value());
        rs.setMessage("Coupon created");
        rs.setData(saved);

        return new ResponseEntity<>(rs,HttpStatus.CREATED);
    }
    
    //Deleting Coupon 
    public ResponseEntity<ResponceStructure<String>> deleteCoupon(Integer id){

        Coupon coupon = couponRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Coupon not found"));

        couponRepo.delete(coupon);

        ResponceStructure<String> rs = new ResponceStructure<>();
        rs.setStatusCode(HttpStatus.OK.value());
        rs.setMessage("Coupon deleted");
        rs.setData("Deleted");

        return new ResponseEntity<>(rs,HttpStatus.OK);
    }
    
    
    //Updating Coupon
    public ResponseEntity<ResponceStructure<Coupon>> updateCoupon(
            Integer id,String expiryDate){

        Coupon coupon = couponRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Coupon not found"));

        coupon.setExpiryDate(LocalDate.parse(expiryDate));

        couponRepo.save(coupon);

        ResponceStructure<Coupon> rs = new ResponceStructure<>();
        rs.setStatusCode(HttpStatus.OK.value());
        rs.setMessage("Coupon updated");
        rs.setData(coupon);

        return new ResponseEntity<>(rs,HttpStatus.OK);
    }
    
    //Finding the Coupon
    public ResponseEntity<ResponceStructure<Coupon>> findCoupon(Integer couponId){

        Coupon coupon = couponRepo.findById(couponId)
                .orElseThrow(() -> 
                        new RuntimeException("Coupon not found"));

        ResponceStructure<Coupon> rs = new ResponceStructure<>();

        rs.setStatusCode(HttpStatus.OK.value());
        rs.setMessage("Coupon fetched successfully");
        rs.setData(coupon);

        return new ResponseEntity<>(rs,HttpStatus.OK);
    }
}
