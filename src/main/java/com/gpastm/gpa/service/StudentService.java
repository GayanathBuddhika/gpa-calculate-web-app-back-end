package com.gpastm.gpa.service;

import java.util.List;

import com.gpastm.gpa.model.Student;

public interface StudentService {

	List<Student> findAll();

	void addCourse(Student student);

	void deleteStudent(String studentId);

}
