package com.tavant.springboot.exception;

public class InvalidDepartmentIdException extends Exception {

	public InvalidDepartmentIdException(String msg) {
		super(msg);
	}

	@Override
	public String toString() {
		return super.toString()+super.getMessage();
	}
	
	
	
}
