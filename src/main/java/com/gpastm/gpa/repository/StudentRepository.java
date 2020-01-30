package com.gpastm.gpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gpastm.gpa.model.Student;

public interface StudentRepository extends JpaRepository<Student, String> {

	Student findByEpNumber(String epnumber);

	Student findByStudentName(String studentName);

	List<Student> findByDepartment_id(String depId);

}
