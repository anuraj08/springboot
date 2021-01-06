package com.tavant.springboot.model;

import javax.naming.InvalidNameException;

import com.tavant.springboot.exception.InvalidLocationIdException;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
//@AllArgsConstructor
public class Location {

	private String locationID;
	private String locationName;
	String specialCharactersString = "!@#$%&*()'+,-./:;<=>?[]^_`{|}";
	
	public Location(String locationID, String locationName) throws InvalidLocationIdException, InvalidNameException {
		super();
		this.setLocationID(locationID);
		this.setLocationName(locationName);
	}
	
	public void setLocationID(String locationID) throws InvalidLocationIdException {
		if(locationID.length()==0) {
			throw new InvalidLocationIdException("Location ID cannot be empty");
		}
		if(Integer.parseInt(locationID)<0) {
			throw new InvalidLocationIdException("Location id cannot be negative");
		}
		for (int i=0; i < locationID.length() ; i++)
		{
			char ch = locationID.charAt(i);
			if(specialCharactersString.contains(Character.toString(ch))) {
				throw new InvalidLocationIdException("Location id cannot contain special character");
			}
		}
		this.locationID = locationID;
	}
	
	public void setLocationName(String locationName) throws InvalidNameException {
		if(locationName.length()==0) {
			throw new InvalidNameException("Location name cannot be empty");
		}
		for (int i=0; i < locationName.length() ; i++)
		{
			char ch = locationName.charAt(i);
			if(specialCharactersString.contains(Character.toString(ch))) {
				throw new InvalidNameException("Location Name cannot contain special character");
			}
		}
		this.locationName = locationName;
	}
	
	
}
