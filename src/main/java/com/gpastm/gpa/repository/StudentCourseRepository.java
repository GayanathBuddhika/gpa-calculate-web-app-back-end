package com.gpastm.gpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gpastm.gpa.model.StudentCourse;

public interface StudentCourseRepository extends JpaRepository<StudentCourse, String> {

	StudentCourse findByStudent_epNumberAndCourse_id(String epnumber, String courseId);

}
