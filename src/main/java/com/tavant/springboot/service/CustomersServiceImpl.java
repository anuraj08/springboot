package com.tavant.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tavant.springboot.dao.CustomersDAO;
import com.tavant.springboot.model.Customers;
@Service

public class CustomersServiceImpl implements CustomersService {

	@Autowired
	CustomersDAO customersDAO;
	
	@Override
	public String addCustomer(Customers customer) {
		return customersDAO.addCustomer(customer);
	}

	@Override
	public Optional<Customers> updateCustomer(int custNumber, Customers customer) {
		return customersDAO.updateCustomer(custNumber, customer);
	}

	@Override
	public String deleteCustomer(int custNumber) {
		return customersDAO.deleteCustomer(custNumber);
	}

	@Override
	public Optional<Customers> getCustomerById(int custNumber) {
		return customersDAO.getCustomerById(custNumber);
	}

	@Override
	public Optional<List<Customers>> getCustomers() {
		return customersDAO.getCustomers();
	}

	@Override
	public boolean CustomerExistById(int custNumber) {
		return customersDAO.CustomerExistById(custNumber);
	}

}
