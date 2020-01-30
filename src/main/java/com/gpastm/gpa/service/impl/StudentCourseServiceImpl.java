package com.gpastm.gpa.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpastm.gpa.model.StudentCourse;
import com.gpastm.gpa.repository.StudentCourseRepository;
import com.gpastm.gpa.service.StudentCourseService;

@Service
@Transactional
public class StudentCourseServiceImpl implements StudentCourseService {

	@Autowired
	StudentCourseRepository studentCourseRepository; 
	
	@Override
	public StudentCourse findStudentCourseById(String studentCourseId) {
		// TODO Auto-generated method stub
		return studentCourseRepository.getOne(studentCourseId) ;
	}

	@Override
	public StudentCourse findStudentCourseByepNumberCourseId(String epnumber, String courseId) {
		// TODO Auto-generated method stub
		return studentCourseRepository.findByStudent_epNumberAndCourse_id(epnumber,courseId);
	}

	@Override
	public void saveStudentCourse(StudentCourse studentCourse) {
		// TODO Auto-generated method stub
		studentCourseRepository.save(studentCourse);
	}

	@Override
	public List<StudentCourse> findCourseByStudentId(String studentId) {
		// TODO Auto-generated method stub
		return studentCourseRepository.findByStudent_id(studentId);
	}

}
