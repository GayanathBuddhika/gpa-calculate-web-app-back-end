package com.gpastm.gpa.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.gpastm.gpa.model.Student;
import com.gpastm.gpa.model.StudentCourse;

public interface StudentService {

	List<Student> findAll();

	void addCourse(Student student);

	void deleteStudent(String studentId);

	List<Student> addstudentBycsvFile(MultipartFile file, String departmentId) throws IOException;

	Student findstudentByEpnumber(String epnumber);




}
