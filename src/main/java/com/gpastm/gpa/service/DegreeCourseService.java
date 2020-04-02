package com.gpastm.gpa.service;

import java.util.List;

import com.gpastm.gpa.model.Course;
import com.gpastm.gpa.model.DegreeCourse;
import com.gpastm.gpa.model.DegreeProgram;

public interface DegreeCourseService {

//	void addDegreeCourse(String id, String degreeProgramId, String lectureId);

	void addDegreeCourseLecture(DegreeCourse degreeCourse);

	List<DegreeCourse> findByDepId(String depId);

	void deleteDegreeCourse(String degreeCourseId);

	List<DegreeCourse> findDegreeCourse(String courseNumber);

	DegreeCourse findDegreeCourseById(String degreeCourseId);


}
