package com.gpastm.gpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gpastm.gpa.model.StudentCourse;

public interface StudentCourseRepository extends JpaRepository<StudentCourse, String> {

	StudentCourse findByStudent_epNumberAndCourse_id(String epnumber, String courseId);

	List<StudentCourse> findByStudent_id(String studentId);


}
