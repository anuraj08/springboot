package com.tavant.springboot.dao;

import java.util.List;
import java.util.Optional;

import com.tavant.springboot.model.Orders;

public interface OrdersDAO {

	public String addOrders(Orders order);
	public String deleteOrders(int orderNumber);
	public Optional<Orders> updateOrders(int orderNumber, Orders order);
	public Optional<Orders> getOrdersById(int orderNumber);
	public Optional<List<Orders>> getOrders();
	public boolean OrdersExistById(int orderNumber);
	
}
