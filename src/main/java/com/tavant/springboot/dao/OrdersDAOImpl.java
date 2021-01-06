package com.tavant.springboot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tavant.springboot.model.Orders;
import com.tavant.springboot.utils.DBUtils;
@Repository

public class OrdersDAOImpl implements OrdersDAO {

	@Autowired
	DBUtils dbUtils;
	
	@Override
	public String addOrders(Orders order) {
		String query = "insert into orders (comments, customerNumber, orderDate, orderNumber, requiredDate, shippedDate, status) values (?,?,?,?,?,?,?)";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		connection = dbUtils.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, order.getComments());
			preparedStatement.setInt(2, order.getCustomerNumber());
			preparedStatement.setString(3, order.getOrderDate());
			preparedStatement.setInt(4, order.getOrderNumber());
			preparedStatement.setDate(5, order.getRequiredDate());
			preparedStatement.setDate(6, order.getShippedDate());
			preparedStatement.setString(7, order.getStatus());
			int result= preparedStatement.executeUpdate();
			if(result>0) {
				return "success";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			dbUtils.closeConnection(connection);
		}
		return "fail";
	}

	@Override
	public String deleteOrders(int orderNumber) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		connection = dbUtils.getConnection();
		String query = "delete from orders where orderNumber = ?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, orderNumber);
			int res = preparedStatement.executeUpdate();
			if(res>0) {
				return "Success";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			dbUtils.closeConnection(connection);
		}
		return "fail";
	}

	@Override
	public Optional<Orders> updateOrders(int orderNumber, Orders order) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "update orders set comments=?, customerNumber=?, orderDate=?,orderNumber=?,requiredDate=?,shippedDate=?,status=? where orderNumber=?";
		String query1 = "select * from orders where orderNumber = ?";
		ResultSet resultSet = null;
		connection = dbUtils.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, order.getComments());
			preparedStatement.setInt(2, order.getCustomerNumber());
			preparedStatement.setString(3, order.getOrderDate());
			preparedStatement.setInt(4, order.getOrderNumber());
			preparedStatement.setDate(5, order.getRequiredDate());
			preparedStatement.setDate(6, order.getShippedDate());
			preparedStatement.setString(7, order.getStatus());
			preparedStatement.setInt(8, orderNumber);
			int res = preparedStatement.executeUpdate();
			System.out.println(res);
			if(res>0) {
				preparedStatement = connection.prepareStatement(query1);
				preparedStatement.setInt(1, order.getOrderNumber());
				resultSet = preparedStatement.executeQuery(); 
				if(resultSet.next()) {
					Orders o = new Orders();
					o.setComments(resultSet.getString("comments"));
					o.setCustomerNumber(resultSet.getInt("customerNumber"));
					o.setOrderDate(resultSet.getString("orderDate"));
					o.setOrderNumber(resultSet.getInt("orderNumber"));
					o.setRequiredDate(resultSet.getDate("requiredDate"));
					o.setShippedDate(resultSet.getDate("shiipedDate"));
					o.setStatus(resultSet.getString("status"));
					return Optional.of(o);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			dbUtils.closeConnection(connection);
		}
		return Optional.empty();
	}

	@Override
	public Optional<Orders> getOrdersById(int orderNumber) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		connection = dbUtils.getConnection();
		String query = "select * from orders where orderNumber = ?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, orderNumber);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				Orders o = new Orders();
				o.setComments(resultSet.getString("comments"));
				o.setCustomerNumber(resultSet.getInt("customerNumber"));
				o.setOrderDate(resultSet.getString("orderDate"));
				o.setOrderNumber(resultSet.getInt("orderNumber"));
				o.setRequiredDate(resultSet.getDate("requiredDate"));
				o.setShippedDate(resultSet.getDate("shiipedDate"));
				o.setStatus(resultSet.getString("status"));
				return Optional.of(o);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			dbUtils.closeConnection(connection);
		}
		return Optional.empty();
	}

	@Override
	public Optional<List<Orders>> getOrders() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Orders> orders = new ArrayList<Orders>();
		
		connection = dbUtils.getConnection();
		String query = "select * from orders";
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				Orders o = new Orders();
				o.setComments(resultSet.getString("comments"));
				o.setCustomerNumber(resultSet.getInt("customerNumber"));
				o.setOrderDate(resultSet.getString("orderDate"));
				o.setOrderNumber(resultSet.getInt("orderNumber"));
				o.setRequiredDate(resultSet.getDate("requiredDate"));
				o.setShippedDate(resultSet.getDate("shiipedDate"));
				o.setStatus(resultSet.getString("status"));
				orders.add(o);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			dbUtils.closeConnection(connection);
		}
		if(orders.isEmpty()) {
			return Optional.empty();
		}
		else {
			return Optional.of(orders);
		}
	}

	@Override
	public boolean OrdersExistById(int orderNumber) {
		Optional<Orders> opt = getOrdersById(orderNumber);
		if(opt.isEmpty()) {
			return false;
		}
		else
			return true;
	}

}
