package com.gpastm.gpa.service;

import java.util.List;

import com.gpastm.gpa.model.Department;

public interface DepartmentService {

	List<Department> findAll();

	void addDepartment(Department department);

	void deleteDepartment(String departmentId);

}
