package com.tavant.springboot.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.tavant.springboot.model.Employee;

public interface EmployeeService {
	
	public String addEmployee(Employee employee);
	public Optional<Employee> updateEmployee(String empID, Employee employee);
	public String deleteEmployee(String empId);
	public Optional<Employee> getEmployeeById(String empID);
	public Optional<List<Employee>> getEmployees();
	
	public boolean employeeExistById(String empId);
	public Optional<Set<String>> checkOfficeCode();

	// We are doing chages in Service class also becoz our DAO class is returning optional data
	
}
