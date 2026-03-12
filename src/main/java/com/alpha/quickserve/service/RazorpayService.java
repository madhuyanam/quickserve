package com.alpha.quickserve.service;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import com.razorpay.*;

@Service
public class RazorpayService {

    private static final String KEY_ID = "rzp_test_xxxxxxxxx";
    private static final String KEY_SECRET = "xxxxxxxxxxxxx";

    public boolean makePayment(double amount){

        try{

            RazorpayClient razorpay =
                    new RazorpayClient(KEY_ID,KEY_SECRET);

            JSONObject options = new JSONObject();

            options.put("amount", amount * 100);
            options.put("currency","INR");
            options.put("receipt","order_rcptid_11");

            Order order = razorpay.orders.create(options);

            if(order != null){
                return true;
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }
}
