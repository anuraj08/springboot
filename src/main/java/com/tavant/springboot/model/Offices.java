package com.tavant.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Offices {
	// We have created offices also because employee table has reference with offices where office code is the foreign key in employee table
	private String officeCode;
	private String city;
	private String phone;
	private String addressLine1;
	private String addressLine2;
	private String state;
	private String country;
	
	private String postalCode;
	private String territory;
	
}
