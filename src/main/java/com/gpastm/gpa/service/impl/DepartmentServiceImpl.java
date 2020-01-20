package com.gpastm.gpa.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpastm.gpa.model.Department;
import com.gpastm.gpa.model.Faculty;
import com.gpastm.gpa.repository.DepartmentRepository;
import com.gpastm.gpa.service.DepartmentService;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService{

	@Autowired
	DepartmentRepository departmentRepository;
	
	@Override
	public List<Department> findAll() {
		// TODO Auto-generated method stub
		return departmentRepository.findAll();
	}

	@Override
	public void addDepartment(Department department) {
		// TODO Auto-generated method stub
		departmentRepository.save(department);
	}

	@Override
	public void deleteDepartment(String departmentId) {
		// TODO Auto-generated method stub
		departmentRepository.deleteById(departmentId);
	}

	@Override
	public Department findDepartmentById(String departmentId) {
		// TODO Auto-generated method stub
		return departmentRepository.getOne(departmentId);
	}

	@Override
	public boolean findUnique(String name, String id) {
		// TODO Auto-generated method stub
		Department department = departmentRepository.findByName(name);
		return department != null && !department.getId().equals(id);
	}

}
