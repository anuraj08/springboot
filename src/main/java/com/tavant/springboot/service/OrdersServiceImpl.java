package com.tavant.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tavant.springboot.dao.OrdersDAO;
import com.tavant.springboot.model.Orders;
@Service

public class OrdersServiceImpl implements OrdersService {

	@Autowired
	OrdersDAO ordersDAO;
	
	@Override
	public String addOrders(Orders order) {
		return ordersDAO.addOrders(order);
	}

	@Override
	public String deleteOrders(int orderNumber) {
		return ordersDAO.deleteOrders(orderNumber);
	}

	@Override
	public Optional<Orders> updateOrders(int orderNumber, Orders order) {
		return ordersDAO.updateOrders(orderNumber, order);
	}

	@Override
	public Optional<Orders> getOrdersById(int orderNumber) {
		return ordersDAO.getOrdersById(orderNumber);
	}

	@Override
	public Optional<List<Orders>> getOrders() {
		return ordersDAO.getOrders();
	}

	@Override
	public boolean OrdersExistById(int orderNumber) {
		return ordersDAO.OrdersExistById(orderNumber);
	}

}
