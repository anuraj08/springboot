package com.tavant.springboot.service;

import java.util.List;
import java.util.Optional;

import com.tavant.springboot.model.Payments;

public interface PaymentsService {

	public String addPayments(Payments payments);
	public Optional<Payments> updateOffice(int custNumber, Payments payments);
	public String deletePayments(int custNumber);
	public Optional<Payments> getPaymentsById(int custNumber);
	public Optional<List<Payments>> getOffices();
	public boolean paymnetsExistById(int custNumber);
	
}
