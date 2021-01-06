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

import com.tavant.springboot.model.OrderDetails;
import com.tavant.springboot.utils.DBUtils;

@Repository
public class OrderDetailsDAOImpl implements OrderDetailsDAO {

	@Autowired
	DBUtils dbUtils;
	
	@Override
	public String addOrderDetails(OrderDetails orderDetails) {
		String query = "insert into orderdetails (orderLineNumber, orderNumber, priceEach, productCode, quantityOrdered) values (?,?,?,?,?)";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		connection = dbUtils.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, orderDetails.getOrderLineNumber());
			preparedStatement.setInt(2, orderDetails.getOrderNumber());
			preparedStatement.setDouble(3, orderDetails.getPriceEach());
			preparedStatement.setString(4, orderDetails.getProductCode());
			preparedStatement.setInt(5, orderDetails.getQuantityOrdered());
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
	public String deleteOrderDetails(int orderNumber) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		connection = dbUtils.getConnection();
		String query = "delete from orderdetails where orderNumber = ?";
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
	public Optional<OrderDetails> updateOrderDetails(int orderNumber, OrderDetails orderDetails) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "update orderdetails set orderLineNumber=?, orderNumber=?, priceEach=?,productCode=?,quantityOrdered=? where orderNumber=?";
		String query1 = "select * from orderdetails where orderNumber = ?";
		ResultSet resultSet = null;
		connection = dbUtils.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, orderDetails.getOrderLineNumber());
			preparedStatement.setInt(2, orderDetails.getOrderNumber());
			preparedStatement.setDouble(3, orderDetails.getPriceEach());
			preparedStatement.setString(4, orderDetails.getProductCode());
			preparedStatement.setInt(5, orderDetails.getQuantityOrdered());
			preparedStatement.setInt(6, orderNumber);
			int res = preparedStatement.executeUpdate();
			System.out.println(res);
			if(res>0) {
				preparedStatement = connection.prepareStatement(query1);
				preparedStatement.setInt(1, orderDetails.getOrderNumber());
				resultSet = preparedStatement.executeQuery(); 
				if(resultSet.next()) {
					OrderDetails od = new OrderDetails();
					od.setOrderLineNumber(resultSet.getInt("orderLineNumber"));
					od.setOrderNumber(resultSet.getInt("orderNumber"));
					od.setPriceEach(resultSet.getDouble("priceEach"));
					od.setProductCode(resultSet.getString("productCode"));
					od.setQuantityOrdered(resultSet.getInt("quantityOrdered"));
					return Optional.of(od);
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
	public Optional<OrderDetails> getOrderDetailsById(int orderNumber) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		connection = dbUtils.getConnection();
		String query = "select * from orderdetails where orderNumber = ?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, orderNumber);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				OrderDetails od = new OrderDetails();
				od.setOrderLineNumber(resultSet.getInt("orderLineNumber"));
				od.setOrderNumber(resultSet.getInt("orderNumber"));
				od.setPriceEach(resultSet.getDouble("priceEach"));
				od.setProductCode(resultSet.getString("productCode"));
				od.setQuantityOrdered(resultSet.getInt("quantityOrdered"));
				return Optional.of(od);
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
	public Optional<List<OrderDetails>> getOrderDetails() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<OrderDetails> orderDetails = new ArrayList<OrderDetails>();
		
		connection = dbUtils.getConnection();
		String query = "select * from orderdetails";
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				OrderDetails od = new OrderDetails();
				od.setOrderLineNumber(resultSet.getInt("orderLineNumber"));
				od.setOrderNumber(resultSet.getInt("orderNumber"));
				od.setPriceEach(resultSet.getDouble("priceEach"));
				od.setProductCode(resultSet.getString("productCode"));
				od.setQuantityOrdered(resultSet.getInt("quantityOrdered"));
				orderDetails.add(od);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			dbUtils.closeConnection(connection);
		}
		if(orderDetails.isEmpty()) {
			return Optional.empty();
		}
		else {
			return Optional.of(orderDetails);
		}
	}

	@Override
	public boolean OrderDetailsExistById(int orderNumber) {
		Optional<OrderDetails> opt = getOrderDetailsById(orderNumber);
		if(opt.isEmpty()) {
			return false;
		}
		else
			return true;
	}

}
