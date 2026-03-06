package com.alpha.quickserve.service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    // 1️⃣ Update Delivery Partner Location
	public String updateDpLoc(Integer partnerid,double latitude,double longitude){

        redisTemplate.opsForGeo()
                .add("deliverypartner:location",
                        new Point(longitude,latitude),
                        partnerid.toString());

        return "Location Updated";
    }

    //Find Nearby Delivery Partners
    public List<String> findNearbyPartners(double latitude,double longitude,double radiusKm){

        Circle searchArea =
                new Circle(new Point(longitude,latitude),
                        new Distance(radiusKm, Metrics.KILOMETERS));

        GeoResults<RedisGeoCommands.GeoLocation<String>> results =
                redisTemplate.opsForGeo()
                        .radius("deliverypartner:location", searchArea);

        if(results == null){
            return List.of();
        }

        return results.getContent()
                .stream()
                .map(result -> result.getContent().getName())
                .collect(Collectors.toList());
    }

}