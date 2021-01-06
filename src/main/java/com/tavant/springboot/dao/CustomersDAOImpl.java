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

import com.tavant.springboot.model.Customers;
import com.tavant.springboot.model.Offices;
import com.tavant.springboot.utils.DBUtils;
@Repository

public class CustomersDAOImpl implements CustomersDAO {

	@Autowired
	DBUtils dbUtils;
	
	@Override
	public String addCustomer(Customers customer) {
		String query = "insert into customers (customerNumber, customerName, contactLastName, contactFirstName, phone, addressLine1, addressLine2, city, country, creditLimit, postalCode, salesRepEmployeeNumber, state) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		connection = dbUtils.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, customer.getCustomerNumber());
			preparedStatement.setString(2, customer.getCustomerName());
			preparedStatement.setString(3, customer.getContactLastName());
			preparedStatement.setString(4, customer.getContactFirstName());
			preparedStatement.setString(5, customer.getPhone());
			preparedStatement.setString(6, customer.getAddressLine1());
			preparedStatement.setString(7, customer.getAddressLine2());
			preparedStatement.setString(8, customer.getCity());
			preparedStatement.setString(9, customer.getCountry());
			preparedStatement.setDouble(10, customer.getCreditLimit());
			preparedStatement.setString(11, customer.getPostalCode());
			preparedStatement.setInt(12, customer.getSalesRepEmployeeNumber());
			preparedStatement.setString(13, customer.getState());
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
	public Optional<Customers> updateCustomer(int custNumber, Customers customer) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "update customers set customerNumber=?, customerName=?,contactLastName=?,contactFirstName=?,phone=?,addressLine1=?,addressLine2=?,city=?,country=?,creditLimit=?,postalCode=?,salesRepEmployeeNumber=?,state=? where customerNumber=?";
		String query1 = "select * from customers where customerNumber = ?";
		ResultSet resultSet = null;
		connection = dbUtils.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, customer.getCustomerNumber());
			preparedStatement.setString(2, customer.getCustomerName());
			preparedStatement.setString(3, customer.getContactLastName());
			preparedStatement.setString(4, customer.getContactFirstName());
			preparedStatement.setString(5, customer.getPhone());
			preparedStatement.setString(6, customer.getAddressLine1());
			preparedStatement.setString(7, customer.getAddressLine2());
			preparedStatement.setString(8, customer.getCity());
			preparedStatement.setString(9, customer.getCountry());
			preparedStatement.setDouble(10, customer.getCreditLimit());
			preparedStatement.setString(11, customer.getPostalCode());
			preparedStatement.setInt(12, customer.getSalesRepEmployeeNumber());
			preparedStatement.setString(13, customer.getState());
			preparedStatement.setInt(14, custNumber);
			int res = preparedStatement.executeUpdate();
			System.out.println(res);
			if(res>0) {
				preparedStatement = connection.prepareStatement(query1);
				preparedStatement.setInt(1, customer.getCustomerNumber());
				resultSet = preparedStatement.executeQuery(); 
				if(resultSet.next()) {
					Customers cust = new Customers();
					cust.setCustomerNumber(resultSet.getInt("customerNumber"));
					cust.setCustomerName(resultSet.getString("customerName"));
					cust.setContactLastName(resultSet.getString("contactLastName"));
					cust.setContactFirstName(resultSet.getString("contactFirstName"));
					cust.setPhone(resultSet.getString("phone"));
					cust.setAddressLine1(resultSet.getString("addressLine1"));
					cust.setAddressLine2(resultSet.getString("addressLine2"));
					cust.setCity(resultSet.getString("city"));
					cust.setCountry(resultSet.getString("country"));
					cust.setCreditLimit(resultSet.getDouble("creditLimit"));
					cust.setPostalCode(resultSet.getString("postalCode"));
					cust.setSalesRepEmployeeNumber(resultSet.getInt("salesResEmployeeNumber"));
					cust.setState(resultSet.getString("state"));
					return Optional.of(cust);
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
	public String deleteCustomer(int custNumber) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		connection = dbUtils.getConnection();
		String query = "delete from customers where customerNumber = ?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, custNumber);
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
	public Optional<Customers> getCustomerById(int custNumber) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		connection = dbUtils.getConnection();
		String query = "select * from customers where customerNumber = ?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, custNumber);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				Customers cust = new Customers();
				cust.setCustomerNumber(resultSet.getInt("customerNumber"));
				cust.setCustomerName(resultSet.getString("customerName"));
				cust.setContactLastName(resultSet.getString("contactLastName"));
				cust.setContactFirstName(resultSet.getString("contactFirstName"));
				cust.setPhone(resultSet.getString("phone"));
				cust.setAddressLine1(resultSet.getString("addressLine1"));
				cust.setAddressLine2(resultSet.getString("addressLine2"));
				cust.setCity(resultSet.getString("city"));
				cust.setCountry(resultSet.getString("country"));
				cust.setCreditLimit(resultSet.getDouble("creditLimit"));
				cust.setPostalCode(resultSet.getString("postalCode"));
				cust.setSalesRepEmployeeNumber(resultSet.getInt("salesResEmployeeNumber"));
				cust.setState(resultSet.getString("state"));
				return Optional.of(cust);
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
	public Optional<List<Customers>> getCustomers() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Customers> customers = new ArrayList<Customers>();
		
		connection = dbUtils.getConnection();
		String query = "select * from customers";
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				Customers cust = new Customers();
				cust.setCustomerNumber(resultSet.getInt("customerNumber"));
				cust.setCustomerName(resultSet.getString("customerName"));
				cust.setContactLastName(resultSet.getString("contactLastName"));
				cust.setContactFirstName(resultSet.getString("contactFirstName"));
				cust.setPhone(resultSet.getString("phone"));
				cust.setAddressLine1(resultSet.getString("addressLine1"));
				cust.setAddressLine2(resultSet.getString("addressLine2"));
				cust.setCity(resultSet.getString("city"));
				cust.setCountry(resultSet.getString("country"));
				cust.setCreditLimit(resultSet.getDouble("creditLimit"));
				cust.setPostalCode(resultSet.getString("postalCode"));
				cust.setSalesRepEmployeeNumber(resultSet.getInt("salesResEmployeeNumber"));
				cust.setState(resultSet.getString("state"));
				customers.add(cust);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			dbUtils.closeConnection(connection);
		}
		if(customers.isEmpty()) {
			return Optional.empty();
		}
		else {
			return Optional.of(customers);
		}
	}

	@Override
	public boolean CustomerExistById(int custNumber) {
		Optional<Customers> opt = getCustomerById(custNumber);
		if(opt.isEmpty()) {
			return false;
		}
		else
			return true;
	}

}
