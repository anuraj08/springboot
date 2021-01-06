package com.tavant.springboot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tavant.springboot.model.Employee;
import com.tavant.springboot.utils.DBUtils;
@Repository

public class EmployeeDAOImpl implements EmployeeDAO {

	@Autowired
	DBUtils dbUtils;
	
	@Override
	public String addEmployee(Employee employee) {
		String query = "insert into employees (employeeNumber, firstName, lastName, extension, jobTitle, email, officeCode, reportsTo) values (?,?,?,?,?,?,?,?)";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		connection = dbUtils.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, employee.getEmployeeNumber());
			preparedStatement.setString(2, employee.getFirstName());
			preparedStatement.setString(3, employee.getLastName());
			preparedStatement.setString(4, employee.getExtension());
			preparedStatement.setString(5, employee.getJobTitle());
			preparedStatement.setString(6, employee.getEmail());
			preparedStatement.setString(7, employee.getOfficeCode());
			preparedStatement.setInt(8, employee.getReportsTo());
			int result= preparedStatement.executeUpdate();
			//System.out.println(result);
			// Here >0 because result contain the count of rows effected by the query
			if(result>0) {
				return "success";
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
	public String deleteEmployee(String empId) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		connection = dbUtils.getConnection();
		String query = "delete from employees where employeeNumber = ?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(empId));
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
	public Optional<Employee> updateEmployee(String empID, Employee employee) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "update employees set employeeNumber=?, firstName=?,lastName=?,extension=?,jobTitle=?,email=?,officeCode=?,reportsTo=? where employeeNumber=?";
		String query1 = "select * from employees where employeeNumber = ?";
		ResultSet resultSet = null;
		connection = dbUtils.getConnection();
		try {
			System.out.println("Hi");
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, employee.getEmployeeNumber());
			preparedStatement.setString(2, employee.getFirstName());
			preparedStatement.setString(3, employee.getLastName());
			preparedStatement.setString(4, employee.getExtension());
			preparedStatement.setString(5, employee.getJobTitle());
			preparedStatement.setString(6, employee.getEmail());
			preparedStatement.setString(7, employee.getOfficeCode());
			preparedStatement.setInt(8, employee.getReportsTo());
			preparedStatement.setInt(9, Integer.parseInt(empID));
			int res = preparedStatement.executeUpdate();
			System.out.println(res);
			if(res>0) {
				preparedStatement = connection.prepareStatement(query1);
				preparedStatement.setInt(1, employee.getEmployeeNumber());
				resultSet = preparedStatement.executeQuery(); 
				if(resultSet.next()) {
					Employee emp = new Employee();
					emp.setEmployeeNumber(resultSet.getInt("employeeNumber"));
					emp.setFirstName(resultSet.getString("firstName"));
					emp.setLastName(resultSet.getString("lastName"));
					emp.setJobTitle(resultSet.getString("jobTitle"));
					emp.setOfficeCode(resultSet.getString("officeCode"));
					emp.setReportsTo(resultSet.getInt("reportsTo"));
					employee.setExtension(resultSet.getString("extension"));
					employee.setEmail(resultSet.getString("email"));
					
					return Optional.of(emp);
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
	public Optional<Employee> getEmployeeById(String empID) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		//Statement statement = null;
		ResultSet resultSet = null;
		connection = dbUtils.getConnection();
		//String query = "select * from employees where employeeNumber = "+empID;
		String query = "select * from employees where employeeNumber = ?";
		try {
			//statement = connection.createStatement();
			//resultSet = statement.executeQuery(query);
			// for prepared statement
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(empID));
			resultSet = preparedStatement.executeQuery(); //Here our query is precompiled in case of preparedStatement
			if(resultSet.next()) {
				Employee employee = new Employee();
				employee.setEmployeeNumber(resultSet.getInt("employeeNumber"));
				employee.setFirstName(resultSet.getString("firstName"));
				employee.setLastName(resultSet.getString("lastName"));
				employee.setJobTitle(resultSet.getString("jobTitle"));
				employee.setOfficeCode(resultSet.getString("officeCode"));
				employee.setReportsTo(resultSet.getInt("reportsTo"));
				employee.setExtension(resultSet.getString("extension"));
				employee.setEmail(resultSet.getString("email"));
				
				return Optional.of(employee);
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
	public Optional<List<Employee>> getEmployees() {
		// First we need to do connection
		Connection connection = null;
		//Statement statement = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Employee> employees = new ArrayList<Employee>();
		
		connection = dbUtils.getConnection();
		String query = "select * from employees";
		try {
//			statement = connection.createStatement();
//			resultSet = statement.executeQuery(query);
			
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			//resultSet.next() method will check whether next record is there or not
			// this next method moves the cursor forward one row from its current position.
			// A ResultSet cursor is initially positioned before the first row; the first call to the method next makes the first row the current row;
			//the second call makes the second row the current row, and so on.
			while(resultSet.next()) {
				//System.out.println(resultSet.getInt("employeeNumber"));
				
				// We are receiving employee records
				Employee employee = new Employee();
				employee.setEmployeeNumber(resultSet.getInt("employeeNumber"));
				employee.setFirstName(resultSet.getString("firstName"));
				employee.setLastName(resultSet.getString("lastName"));
				employee.setJobTitle(resultSet.getString("jobTitle"));
				employee.setOfficeCode(resultSet.getString("officeCode"));
				employee.setReportsTo(resultSet.getInt("reportsTo"));
				employee.setExtension(resultSet.getString("extension"));
				employee.setEmail(resultSet.getString("email"));
				
				employees.add(employee);
				//return Optional.of(employees);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			dbUtils.closeConnection(connection);
		}
		if(employees.isEmpty()) {
			return Optional.empty();
		}
		else {
			return Optional.of(employees);
		}
		//return Optional.empty();
	}

	@Override
	public boolean employeeExistById(String empId) {
		
		Optional<Employee> opt = getEmployeeById(empId);
		if(opt.isEmpty()) {
			return false;
		}
		else
			return true;
	}

	@Override
	public Optional<Set<String>> checkOfficeCode() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Set<String> oCode = new HashSet<String>();
		String query = "select officeCode from employees";
		connection = dbUtils.getConnection();
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				oCode.add(resultSet.getString("officeCode"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			dbUtils.closeConnection(connection);
		}
		if(oCode.isEmpty()) {
			return Optional.empty();
		}
		else
			return Optional.of(oCode);
	}

}
