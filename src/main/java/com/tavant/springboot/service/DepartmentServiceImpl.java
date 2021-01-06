package com.tavant.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tavant.springboot.dao.DepartmentDAO;
import com.tavant.springboot.model.Department;
@Service

public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	DepartmentDAO departmentDAO;
	
	@Override
	public String addDepartment(Department department) {
		// TODO Auto-generated method stub
		return departmentDAO.addDepartment(department);
	}

	@Override
	public Optional<Department> updateDepartment(String deptID, Department department) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteDepartment(String deptID) {
		// TODO Auto-generated method stub
		return departmentDAO.deleteDepartment(deptID);
	}

	@Override
	public Optional<Department> getDepartmentById(String deptID) {
		// TODO Auto-generated method stub
		return departmentDAO.getDepartmentById(deptID);
	}

	@Override
	public Optional<List<Department>> getDepartments() {
		// TODO Auto-generated method stub
		return departmentDAO.getDepartments();
	}

	@Override
	public boolean departmentExistById(String deptID) {
		// TODO Auto-generated method stub
		return departmentDAO.departmentExistById(deptID);
	}

}
