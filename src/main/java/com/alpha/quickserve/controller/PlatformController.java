package com.alpha.quickserve.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.quickserve.entity.Coupon;
import com.alpha.quickserve.responcestructure.ResponceStructure;
import com.alpha.quickserve.service.PlatformService;

@RestController
@RequestMapping("/platform")
public class PlatformController {

    @Autowired
    private PlatformService platformService;

    @PostMapping("/createcoupon")
    public ResponseEntity<ResponceStructure<Coupon>> createCoupon(
            @RequestBody Coupon coupon){

        return platformService.createCoupon(coupon);
    }

    @DeleteMapping("/deletecoupon")
    public ResponseEntity<ResponceStructure<String>> deleteCoupon(
            @RequestParam Integer couponId){

        return platformService.deleteCoupon(couponId);
    }

    @PatchMapping("/updatecoupon")
    public ResponseEntity<ResponceStructure<Coupon>> updateCoupon(
            @RequestParam Integer couponId,
            @RequestParam String expiryDate){

        return platformService.updateCoupon(couponId,expiryDate);
    }
    @GetMapping("/findcoupon")
    public ResponseEntity<ResponceStructure<Coupon>> findCoupon(
            @RequestParam Integer couponId){

        return platformService.findCoupon(couponId);
    }
}