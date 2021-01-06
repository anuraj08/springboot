package com.tavant.springboot.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Repository;

import com.tavant.springboot.model.Location;
@Repository

public class LocationDAOImpl implements LocationDAO {

	private List<Location> locations = new ArrayList<Location>();
	@Override
	public String addLocation(Location location) {
		// TODO Auto-generated method stub
		boolean result = locations.add(location);
		if(result)
			return "success";
		else
			return "fail";
	}

	@Override
	public Optional<Location> updateLocation(String locationID, Location location) {
		// TODO Auto-generated method stub
		//return null;
		return Optional.empty();
	}

	@Override
	public String deleteLocation(String locationID) {
		// TODO Auto-generated method stub
		
		//With stream
		try {
		locations = locations.stream()
				.filter(e->!(e.getLocationID().equals(locationID)))
				.collect(Collectors.toList());
		return "success";
		}
		catch(Exception e) {
			e.printStackTrace();
			return "fail";
		}
		
//		boolean result = locations.remove(this.getLocationById(locationID));
//		if(result)
//			return "success";
//		else
//			return "fail";
	}

	@Override
	public Optional<Location> getLocationById(String locationID) {
		// TODO Auto-generated method stub
		
		//With stream
		Stream<Location> loc = locations.stream();
		return loc.filter(e->e.getLocationID().equals(locationID)).findFirst();
		
//		for(Location location : locations) {
//			if(location.getLocationID().equals(locationID)) {
//				return Optional.of(location);
//			}
//		}
//		return Optional.empty();
	}

	@Override
	public Optional<List<Location>> getLocations() {
		// TODO Auto-generated method stub
		if(locations.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(locations);
	}

	@Override
	public boolean locationExistById(String locationID) {
		// TODO Auto-generated method stub
		return locations.contains(this.getLocationById(locationID));
	}

}
