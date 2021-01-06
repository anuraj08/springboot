package com.tavant.springboot.service;

import java.util.List;
import java.util.Optional;

import com.tavant.springboot.model.Location;

public interface LocationService {

	public String addLocation(Location location);
	public Optional<Location> updateLocation(String locationID, Location location);
	public String deleteLocation(String locationID);
	public Optional<Location> getLocationById(String locationID);
	public Optional<List<Location>> getLocations();
	public boolean locationExistById(String locationID);
}
