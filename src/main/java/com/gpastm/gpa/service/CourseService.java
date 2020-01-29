package com.gpastm.gpa.service;

import java.util.List;

import com.gpastm.gpa.model.Course;

public interface CourseService {

	List<Course> findAll();

	Course addCourse(Course course);

	void deleteCourse(String courseId);

	Course findCourseById(String id);

	boolean findUnique(String name, String id);

}
