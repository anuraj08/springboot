package com.tavant.springboot.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Repository;

import com.tavant.springboot.model.Department;
@Repository

public class DepartmentDAOImpl implements DepartmentDAO {

	private List<Department> departments = new ArrayList<Department>();
	@Override
	public String addDepartment(Department department) {
		// TODO Auto-generated method stub
		boolean result = departments.add(department);
		if(result)
			return "success";
		else
			return "fail";
	}

	@Override
	public Optional<Department> updateDepartment(String deptID, Department department) {
		// TODO Auto-generated method stub
		//return null;
		return Optional.empty();
	}

	@Override
	public String deleteDepartment(String deptID) {
		// TODO Auto-generated method stub
		
		//With stream
		try {
		departments = departments.stream()
				.filter(e->!(e.getDeptID().equals(deptID)))
				.collect(Collectors.toList());
		return "success";
		}
		catch(Exception e) {
			e.printStackTrace();
			return "fail";
		}
		
//		boolean result = departments.remove(this.getDepartmentById(deptID));
//		if(result)
//			return "success";
//		else
//			return "fail";
	}

	@Override
	public Optional<Department> getDepartmentById(String deptID) {
		// TODO Auto-generated method stub

		//With stream
		
		Stream<Department> department = departments.stream();
		return department.filter(e->e.getDeptID().equals(deptID)).findFirst();
		
//		for(Department department : departments) {
//			if(department.getDeptID().equals(deptID)) {
//				return Optional.of(department);
//			}
//		}
//		return Optional.empty();
//		//return null;
	}

	@Override
	public Optional<List<Department>> getDepartments() {
		// TODO Auto-generated method stub
		if(departments.isEmpty()) {
			return Optional.empty();
		}
		//return departments;
		return Optional.of(departments);
	}

	@Override
	public boolean departmentExistById(String deptID) {
		// TODO Auto-generated method stub
		return departments.contains(this.getDepartmentById(deptID));
	}

}
