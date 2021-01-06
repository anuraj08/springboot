package com.tavant.springboot.model;

import com.tavant.springboot.exception.InvalidDepartmentIdException;
import com.tavant.springboot.exception.InvalidLocationIdException;
import com.tavant.springboot.exception.InvalidNameException;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
//@AllArgsConstructor
public class Department {

	private String deptID;
	private String deptName;
	private String locID;
	String specialCharactersString = "!@#$%&*()'+,-./:;<=>?[]^_`{|}";
	
	public Department(String deptId, String deptName, String locID) throws InvalidDepartmentIdException, InvalidNameException, InvalidLocationIdException {
		super();
		this.setDeptID(deptID);
		this.setDeptName(deptName);
		this.setLocID(locID);
	}
	
	public void setDeptID(String deptID) throws InvalidDepartmentIdException {
		if(deptID.length()==0) {
			throw new InvalidDepartmentIdException("Department id cannot be empty");
		}
		if(Integer.parseInt(deptID)<0) {
			throw new InvalidDepartmentIdException("Department id cannot be negative");
		}
		for (int i=0; i < deptID.length() ; i++)
		{
			char ch = deptID.charAt(i);
			if(specialCharactersString.contains(Character.toString(ch))) {
				throw new InvalidDepartmentIdException("Department ID cannot contain special character");
			}
		}
		this.deptID = deptID;
	}
	
	public void setDeptName(String deptName) throws InvalidNameException {
		if(deptName.length()==0) {
			throw new InvalidNameException("Department name cannot be empty");
		}
		for (int i=0; i < deptName.length() ; i++)
		{
			char ch = deptName.charAt(i);
			if(specialCharactersString.contains(Character.toString(ch))) {
				throw new InvalidNameException("Department name cannot contain special character");
			}
		}
		this.deptName = deptName;
	}
	
	public void setLocID(String locID) throws InvalidLocationIdException {
		if(locID.length()==0) {
			throw new InvalidLocationIdException("Location ID cannot be empty");
		}
		if(Integer.parseInt(locID)<0) {
			throw new InvalidLocationIdException("Location id cannot be negative");
		}
		for (int i=0; i < locID.length() ; i++)
		{
			char ch = locID.charAt(i);
			if(specialCharactersString.contains(Character.toString(ch))) {
				throw new InvalidLocationIdException("Location ID cannot contain special character");
			}
		}
		this.locID = locID;
	}
	
	
	
}
