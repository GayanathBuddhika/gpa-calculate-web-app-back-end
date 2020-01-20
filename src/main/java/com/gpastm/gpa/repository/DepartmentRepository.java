package com.gpastm.gpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gpastm.gpa.model.Department;

public interface DepartmentRepository extends JpaRepository<Department,String>{

	Department findByName(String name);

	

}
