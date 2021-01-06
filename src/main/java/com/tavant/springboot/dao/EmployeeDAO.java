package com.tavant.springboot.dao;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.tavant.springboot.model.Employee;

public interface EmployeeDAO {

	public String addEmployee(Employee employee);
	public String deleteEmployee(String empId);
		
	/*These three may throw null pointer exception
	  So will use optional class
	public Employee updateEmployee(String empID, Employee employee);
	public Employee getEmployeeById(String empID);
	public List<Employee> getEmployees();
	*/
	
	public Optional<Employee> updateEmployee(String empID, Employee employee);
	public Optional<Employee> getEmployeeById(String empID);
	public Optional<List<Employee>> getEmployees();
	public boolean employeeExistById(String empId);
	
	public Optional<Set<String>> checkOfficeCode();
}
