package com.tavant.springboot.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tavant.springboot.dao.EmployeeDAO;
import com.tavant.springboot.model.Employee;
@Service()

public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeDAO employeeDAO;
	
	@Override
	public String addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return employeeDAO.addEmployee(employee);

	}

	@Override
	public Optional<Employee> updateEmployee(String empID, Employee employee) {
		// TODO Auto-generated method stub
		return employeeDAO.updateEmployee(empID, employee);
	}

	@Override
	public String deleteEmployee(String empId) {
		// TODO Auto-generated method stub
		return employeeDAO.deleteEmployee(empId);
	}

	@Override
	public Optional<Employee> getEmployeeById(String empID) {
		// TODO Auto-generated method stub
		return employeeDAO.getEmployeeById(empID);
	}

	@Override
	public Optional<List<Employee>> getEmployees() {
		// TODO Auto-generated method stub
		return employeeDAO.getEmployees();
	}
	
	@Override
	public boolean employeeExistById(String empId) {
		// TODO Auto-generated method stub
		
		return employeeDAO.employeeExistById(empId);
	}

	@Override
	public Optional<Set<String>> checkOfficeCode() {
		// TODO Auto-generated method stub
		return employeeDAO.checkOfficeCode();
	}

}
