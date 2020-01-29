package com.gpastm.gpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gpastm.gpa.model.Course;

public interface CourseReposotory extends JpaRepository<Course, String> {

	Course findByName(String name);

}
