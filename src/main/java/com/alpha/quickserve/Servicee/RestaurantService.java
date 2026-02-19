package com.alpha.quickserve.Servicee;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import com.alpha.quickserve.DTO.RestaurantDTO;
import com.alpha.quickserve.ResponceStructure.ResponceStructure;
import com.alpha.quickserve.entity.Address;
import com.alpha.quickserve.entity.Item;
import com.alpha.quickserve.entity.Restaurant;
import com.alpha.quickserve.repository.RestaurantRepo;

@Service
public class RestaurantService {
	@Autowired
	private RestaurantRepo restaurantrepo;

	@Autowired
	private RestTemplate resttemplate;

	public ResponseEntity<ResponceStructure<Restaurant>> save(@RequestBody RestaurantDTO rdto) {
		Restaurant r = new Restaurant();

		r.setName(rdto.getName());
		r.setMobno(rdto.getMob());
		r.setMail(rdto.getMail());
		r.setDescription(rdto.getDescription());
		r.setPackagingFee(rdto.getPackagingFee());
		r.setType(rdto.getType());

		String url = "https://us1.locationiq.com/v1/reverse?key=pk.7de45e6b5eecd2b0cac58b13e3b3012e&lat="
				+ rdto.getCoordinates().getLatitude() + "&lon= " + rdto.getCoordinates().getLongitude()
				+ "&format=json&";

		Map<String, Object> response = resttemplate.getForObject(url, Map.class);

		Map<String, Object> addressMap = (Map<String, Object>) response.get("address");

		Address address = new Address();

		address.setLatitude(rdto.getCoordinates().getLatitude());
		address.setLongitude(rdto.getCoordinates().getLongitude());

		address.setArea((String) addressMap.get("suburb"));
		address.setCity((String) addressMap.get("city"));
		address.setDistrict((String) addressMap.get("county"));
		address.setState((String) addressMap.get("state"));
		address.setCountry((String) addressMap.get("country"));
		address.setPincode((String) addressMap.get("postcode"));

		r.setAddress(address);
		restaurantrepo.save(r);
		ResponceStructure<Restaurant> rs = new ResponceStructure<Restaurant>();
		rs.setStatusCode(HttpStatus.CREATED.value());
		rs.setMessage("Restauarant saved successfully");
		rs.setData(r);

		return new ResponseEntity<ResponceStructure<Restaurant>>(rs, HttpStatus.CREATED);
	}
	
	

	public ResponseEntity<ResponceStructure<Restaurant>> findrestaurant(long mobno) {

	    Restaurant restaurant = restaurantrepo.findByMobno(mobno)
	            .orElseThrow(() -> new RuntimeException("Restaurant not found"));

	    ResponceStructure<Restaurant> rs = new ResponceStructure<>();
	    rs.setStatusCode(HttpStatus.OK.value());
	    rs.setMessage("Restaurant fetched successfully");
	    rs.setData(restaurant);

	    return new ResponseEntity<>(rs, HttpStatus.OK);
	}
	
	

	public ResponseEntity<ResponceStructure<Restaurant>> deleteCustomer(long mobno) {

	    Restaurant restaurant = restaurantrepo.findByMobno(mobno)
	            .orElseThrow(() -> new RuntimeException("Restaurant not found"));

	    restaurantrepo.delete(restaurant);

	    ResponceStructure<Restaurant> rs = new ResponceStructure<>();
	    rs.setStatusCode(HttpStatus.OK.value());
	    rs.setMessage("Restaurant Deleted Successfully");
	    rs.setData(null);

	    return new ResponseEntity<>(rs, HttpStatus.OK);
	}

	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	
	public ResponseEntity<ResponceStructure<Restaurant>> addItemToMenu(
	        long restaurantmobno, Item item) {

	    Restaurant restaurant = restaurantrepo.findByMobno(restaurantmobno)
	            .orElseThrow(() ->
	                    new RuntimeException("Restaurant not found"));

	    // Add item to existing menu
	    restaurant.getMenuItems().add(item);

	    restaurantrepo.save(restaurant);

	    ResponceStructure<Restaurant> rs =
	            new ResponceStructure<>();

	    rs.setStatusCode(HttpStatus.OK.value());
	    rs.setMessage("Item added to menu successfully");
	    rs.setData(restaurant);

	    return new ResponseEntity<>(rs, HttpStatus.OK);
	}
	
	
	
	
}

