package com.tavant.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tavant.springboot.dao.PaymentsDAO;
import com.tavant.springboot.model.Payments;
@Service

public class PaymentsServiceImpl implements PaymentsService {

	@Autowired
	PaymentsDAO paymentsDAO;
	
	@Override
	public String addPayments(Payments payments) {
		return paymentsDAO.addPayments(payments);
	}

	@Override
	public Optional<Payments> updateOffice(int custNumber, Payments payments) {
		return paymentsDAO.updateOffice(custNumber, payments);
	}

	@Override
	public String deletePayments(int custNumber) {
		return paymentsDAO.deletePayments(custNumber);
	}

	@Override
	public Optional<Payments> getPaymentsById(int custNumber) {
		return paymentsDAO.getPaymentsById(custNumber);
	}

	@Override
	public Optional<List<Payments>> getOffices() {
		return paymentsDAO.getOffices();
	}

	@Override
	public boolean paymnetsExistById(int custNumber) {
		return paymentsDAO.paymnetsExistById(custNumber);
	}

}
