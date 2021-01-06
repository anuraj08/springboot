package com.tavant.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tavant.springboot.dao.OrderDetailsDAO;
import com.tavant.springboot.model.OrderDetails;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {

	@Autowired
	OrderDetailsDAO orderDetailsDAO;
	
	@Override
	public String addOrderDetails(OrderDetails orderDetails) {
		return orderDetailsDAO.addOrderDetails(orderDetails);
	}

	@Override
	public String deleteOrderDetails(int orderNumber) {
		return orderDetailsDAO.deleteOrderDetails(orderNumber);
	}

	@Override
	public Optional<OrderDetails> updateOrderDetails(int orderNumber, OrderDetails orderDetails) {
		return orderDetailsDAO.updateOrderDetails(orderNumber, orderDetails);
	}

	@Override
	public Optional<OrderDetails> getOrderDetailsById(int orderNumber) {
		return orderDetailsDAO.getOrderDetailsById(orderNumber);
	}

	@Override
	public Optional<List<OrderDetails>> getOrderDetails() {
		return orderDetailsDAO.getOrderDetails();
	}

	@Override
	public boolean OrderDetailsExistById(int orderNumber) {
		return orderDetailsDAO.OrderDetailsExistById(orderNumber);
	}

}
