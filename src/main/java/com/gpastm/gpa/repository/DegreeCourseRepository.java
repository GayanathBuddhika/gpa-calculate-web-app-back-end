package com.gpastm.gpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gpastm.gpa.model.Course;
import com.gpastm.gpa.model.DegreeCourse;

public interface DegreeCourseRepository extends JpaRepository<DegreeCourse, String>{

	List<DegreeCourse> findByDegreeProgram_department_id(String depId);

}
