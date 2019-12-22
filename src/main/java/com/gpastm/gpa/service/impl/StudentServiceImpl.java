package com.gpastm.gpa.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gpastm.gpa.model.Department;
import com.gpastm.gpa.model.Student;
import com.gpastm.gpa.repository.StudentRepository;
import com.gpastm.gpa.service.DepartmentService;
import com.gpastm.gpa.service.StudentService;

import readCsv.ReadStudentCsvFile;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	DepartmentService departmentService;

	@Override
	public List<Student> findAll() {
		// TODO Auto-generated method stub
		return studentRepository.findAll();
	}

	@Override
	public void addCourse(Student student) {
		// TODO Auto-generated method stub
		studentRepository.save(student);
	}

	@Override
	public void deleteStudent(String studentId) {
		// TODO Auto-generated method stub
		studentRepository.deleteById(studentId);
	}

	@Override
	public List<Student> addstudentBycsvFile(MultipartFile file, String departmentId) throws IOException {
		// TODO Auto-generated method stub
		List<Student> studentsList  = ReadStudentCsvFile.read(Student.class,file.getInputStream());
		
		Department department = departmentService.findDepartmentById(departmentId);
		
		List<Student> students = new ArrayList<Student>();
		
		for(Student st : studentsList) {
			
			st.department = department;
			students.add(st);
			}
		

		saveStudentList(students);
		return students ;
	}
	
	
	
	public void saveStudentList(List<Student> students) {
		
		studentRepository.saveAll(students).forEach(students::add);
	}

	@Override
	public Student findstudentByEpnumber(String epnumber) {
		// TODO Auto-generated method stub
		return studentRepository.findByEpNumber(epnumber);
	}

}
