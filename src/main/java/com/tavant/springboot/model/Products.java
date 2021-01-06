package com.tavant.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Products {

	private double buyPrice;
	private double MSRP;
	private String productCode;
	private String productDescription;
	private String productLine;
	private String productName;
	private String productScale;
	private String productVendor;
	private int qualityInStock;
	
}
