package com.tavant.springboot.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidSalaryException extends Exception {
	
	public InvalidSalaryException(String msg) {
		
		super(msg);
	}
	
	@Override
	public String toString() {
		
		return super.toString()+super.getMessage();
	}
}
