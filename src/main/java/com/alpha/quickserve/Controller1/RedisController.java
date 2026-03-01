package com.alpha.quickserve.Controller1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alpha.quickserve.Servicee.RedisService;


@RestController
@RequestMapping("/redis")
public class RedisController {
	
	    @Autowired
	    private RedisService redisService;

	  
	}

