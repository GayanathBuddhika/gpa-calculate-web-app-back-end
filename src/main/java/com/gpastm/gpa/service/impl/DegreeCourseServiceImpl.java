package com.gpastm.gpa.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpastm.gpa.model.Course;
import com.gpastm.gpa.model.DegreeCourse;
import com.gpastm.gpa.model.DegreeProgram;
import com.gpastm.gpa.model.Lecture;
import com.gpastm.gpa.repository.DegreeCourseRepository;
import com.gpastm.gpa.service.CourseService;
import com.gpastm.gpa.service.DegreeCourseService;
import com.gpastm.gpa.service.DegreeProgramService;
import com.gpastm.gpa.service.LectureService;

@Service
@Transactional
public class DegreeCourseServiceImpl implements DegreeCourseService {
	
	@Autowired
	DegreeCourseRepository degreeCourseRepository;
	
	@Autowired
	CourseService courseService;
	
	@Autowired
	LectureService lectureService;
	
	@Autowired
	DegreeProgramService degreeProgramService;

	@Override
	public void addDegreeCourseLecture(DegreeCourse degreeCourse) {
		// TODO Auto-generated method stub
		degreeCourseRepository.save(degreeCourse);
	}

	@Override
	public List<DegreeCourse> findByDepId(String depId) {
		// TODO Auto-generated method stub
		return degreeCourseRepository.findByDegreeProgram_department_id(depId);
	}

	@Override
	public void deleteDegreeCourse(String degreeCourseId) {
		// TODO Auto-generated method stub
		
		degreeCourseRepository.deleteById(degreeCourseId);
		
	}

	@Override
	public List<DegreeCourse> findDegreeCourse(String courseNumber) {
		// TODO Auto-generated method stub
		return degreeCourseRepository.findByUser_id(courseNumber);
	}

	@Override
	public DegreeCourse findDegreeCourseById(String degreeCourseId) {
		// TODO Auto-generated method stub
		return degreeCourseRepository.getOne(degreeCourseId);
	}

	

//	public void addDegreeCourse(String id, String degreeProgramId, String lectureId) {
//		// TODO Auto-generated method stub
//		
//		Course course = courseService.findCourseById(id);
//		DegreeProgram degreeProgram = degreeProgramService.findDegreeProgramById(degreeProgramId); 
//		Lecture lecture = lectureService.findLecturebyId(lectureId);
//		
//		DegreeCourse degreecourse = new DegreeCourse();
//		
//		degreecourse.course = course;
//		degreecourse.degreeProgram = degreeProgram;
//		degreecourse.lecture = lecture;
//		
//		
//		degreeCourseRepository.save(degreecourse);
//		
//		
//		
//	}

}
