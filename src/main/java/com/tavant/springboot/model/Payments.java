package com.tavant.springboot.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payments {

	private double amount;
	private String checkNumber;
	private int customerNumber;
	private Date paymentDate;
}
