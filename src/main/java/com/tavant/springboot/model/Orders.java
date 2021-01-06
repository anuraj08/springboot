package com.tavant.springboot.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {

	private String comments;
	private int customerNumber;
	private String orderDate;
	private int orderNumber;
	private Date requiredDate;
	private Date shippedDate;
	private String status;
	
}
