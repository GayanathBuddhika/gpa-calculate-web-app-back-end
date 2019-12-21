package com.gpastm.gpa.service;

import java.util.List;

import com.gpastm.gpa.model.Course;

public interface CourseService {

	List<Course> findAll();

	void addCourse(Course course);

	void deleteCourse(String courseId);

}
