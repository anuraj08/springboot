package com.tavant.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetails {

	private int orderLineNumber;
	private int orderNumber;
	private double priceEach;
	private String productCode;
	private int quantityOrdered;
	
}
