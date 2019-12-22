package com.gpastm.gpa.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpastm.gpa.model.Course;
import com.gpastm.gpa.repository.CourseReposotory;
import com.gpastm.gpa.service.CourseService;

@Service
@Transactional
public class CourseServiceImpl implements CourseService{
	
	@Autowired
	CourseReposotory courseReposotory;

	@Override
	public List<Course> findAll() {
		// TODO Auto-generated method stub
		return courseReposotory.findAll();
	}

	@Override
	public void addCourse(Course course) {
		// TODO Auto-generated method stub
		courseReposotory.save(course);
	}

	@Override
	public void deleteCourse(String courseId) {
		// TODO Auto-generated method stub
		courseReposotory.deleteById(courseId);
	}

	@Override
	public Course findCourseById(String id) {
		// TODO Auto-generated method stub
		return courseReposotory.getOne(id);
	}

}
