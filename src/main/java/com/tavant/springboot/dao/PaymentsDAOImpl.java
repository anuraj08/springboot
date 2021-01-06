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

import com.tavant.springboot.model.Payments;
import com.tavant.springboot.utils.DBUtils;
@Repository

public class PaymentsDAOImpl implements PaymentsDAO {

	@Autowired
	DBUtils dbUtils;
	
	@Override
	public String addPayments(Payments payments) {
		String query = "insert into payments (customerNumber, checkNumber, paymentDate, amount) values (?,?,?,?)";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		connection = dbUtils.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, payments.getCustomerNumber());
			preparedStatement.setString(2, payments.getCheckNumber());
			preparedStatement.setDate(3, payments.getPaymentDate());
			preparedStatement.setDouble(4, payments.getAmount());
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
	public Optional<Payments> updateOffice(int custNumber, Payments payments) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "update payments set customerNumber=?, checkNumber=?, paymentDate=?,amount=? where customerNumber=?";
		String query1 = "select * from payments where customerNumber = ?";
		ResultSet resultSet = null;
		connection = dbUtils.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, payments.getCustomerNumber());
			preparedStatement.setString(2, payments.getCheckNumber());
			preparedStatement.setDate(3, payments.getPaymentDate());
			preparedStatement.setDouble(4, payments.getAmount());
			preparedStatement.setInt(5, custNumber);
			int res = preparedStatement.executeUpdate();
			System.out.println(res);
			if(res>0) {
				preparedStatement = connection.prepareStatement(query1);
				preparedStatement.setInt(1, payments.getCustomerNumber());
				resultSet = preparedStatement.executeQuery(); 
				if(resultSet.next()) {
					Payments p = new Payments();
					p.setCustomerNumber(resultSet.getInt("customerNumber"));
					p.setCheckNumber(resultSet.getString("checkNumber"));
					p.setPaymentDate(resultSet.getDate("paymentDate"));
					p.setAmount(resultSet.getDouble("amount"));
					return Optional.of(p);
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
	public String deletePayments(int custNumber) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		connection = dbUtils.getConnection();
		String query = "delete from payments where customerNumber = ?";
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
	public Optional<Payments> getPaymentsById(int custNumber) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		connection = dbUtils.getConnection();
		String query = "select * from payments where customerNumber = ?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, custNumber);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				Payments p = new Payments();
				p.setCustomerNumber(resultSet.getInt("customerNumber"));
				p.setCheckNumber(resultSet.getString("checkNumber"));
				p.setPaymentDate(resultSet.getDate("paymentDate"));
				p.setAmount(resultSet.getDouble("amount"));
				return Optional.of(p);
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
	public Optional<List<Payments>> getOffices() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Payments> payments = new ArrayList<Payments>();
		
		connection = dbUtils.getConnection();
		String query = "select * from payments";
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				Payments p = new Payments();
				p.setCustomerNumber(resultSet.getInt("customerNumber"));
				p.setCheckNumber(resultSet.getString("checkNumber"));
				p.setPaymentDate(resultSet.getDate("paymentDate"));
				p.setAmount(resultSet.getDouble("amount"));
				payments.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			dbUtils.closeConnection(connection);
		}
		if(payments.isEmpty()) {
			return Optional.empty();
		}
		else {
			return Optional.of(payments);
		}
	}

	@Override
	public boolean paymnetsExistById(int custNumber) {
		Optional<Payments> opt = getPaymentsById(custNumber);
		if(opt.isEmpty()) {
			return false;
		}
		else
			return true;
	}

}
