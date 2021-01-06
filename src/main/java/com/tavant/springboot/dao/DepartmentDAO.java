package com.tavant.springboot.dao;

import java.util.List;
import java.util.Optional;

import com.tavant.springboot.model.Department;

public interface DepartmentDAO {

	public String addDepartment(Department department);
	public Optional<Department> updateDepartment(String deptID, Department department);
	public String deleteDepartment(String deptID);
	public Optional<Department> getDepartmentById(String deptID);
	public Optional<List<Department>> getDepartments();
	public boolean departmentExistById(String deptID);

}
