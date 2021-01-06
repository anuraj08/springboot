package com.tavant.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tavant.springboot.dao.LocationDAO;
import com.tavant.springboot.model.Location;
@Service

public class LocationServiceImpl implements LocationService {

	@Autowired
	LocationDAO locationDAO;
	@Override
	public String addLocation(Location location) {
		// TODO Auto-generated method stub
		return locationDAO.addLocation(location);
	}

	@Override
	public Optional<Location> updateLocation(String locationID, Location location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteLocation(String locationID) {
		// TODO Auto-generated method stub
		return locationDAO.deleteLocation(locationID);
	}

	@Override
	public Optional<Location> getLocationById(String locationID) {
		// TODO Auto-generated method stub
		return locationDAO.getLocationById(locationID);
	}

	@Override
	public Optional<List<Location>> getLocations() {
		// TODO Auto-generated method stub
		return locationDAO.getLocations();
	}

	@Override
	public boolean locationExistById(String locationID) {
		// TODO Auto-generated method stub
		return locationDAO.locationExistById(locationID);
	}

}
