package com.gpastm.gpa.controler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.NotAcceptableStatusException;

import com.gpastm.gpa.model.Course;
import com.gpastm.gpa.model.DegreeCourse;
import com.gpastm.gpa.model.DegreeProgram;
import com.gpastm.gpa.model.Lecture;
import com.gpastm.gpa.model.Response;
import com.gpastm.gpa.model.User;
import com.gpastm.gpa.service.CourseService;
import com.gpastm.gpa.service.DegreeCourseService;
import com.gpastm.gpa.service.DegreeProgramService;
import com.gpastm.gpa.service.LectureService;
import com.gpastm.gpa.service.UserService;




@Controller
@RequestMapping("/course")
public class CourseController {

	
	@Autowired
	CourseService courseService; 
	
	
	@Autowired
	DegreeCourseService degreeCourseService;
	
	@Autowired
	LectureService lectureService;
	
	@Autowired
    DegreeProgramService degreeProgramService; 
	
	@Autowired
	UserService userService;
	
	@GetMapping("/findAllcourse")
	public ResponseEntity<List<Course>> findAllCourse(){
		return new ResponseEntity<List<Course>>(courseService.findAll(), HttpStatus.OK);
	}

	
	@GetMapping("/findAllDepCourseByDepId/{depId}")
	public ResponseEntity<List<DegreeCourse>> findAllDepCourse(@PathVariable String depId){
		return new ResponseEntity<List<DegreeCourse>>(degreeCourseService.findByDepId(depId), HttpStatus.OK);
	}

	
	

	@PostMapping("/addCourse")
    public  ResponseEntity<Map<String, Object>> addCourse(
  		@RequestBody Course course,
  		@RequestParam("degreeProgramId") String degreeProgramId,
		@RequestParam("lecturId") String lectureId){
		Map<String, Object> mapCourseDegreee = new HashMap<>();
       
		Course csavedCourse = addcourse(course);
		
		DegreeProgram degree = degreeProgramService.findDegreeProgramById(degreeProgramId) ;
		User leture = userService.findLecturebyId(lectureId);

		DegreeCourse degreeCourse = new DegreeCourse();
		degreeCourse.setDegreeProgram(degree);
		degreeCourse.setUser(leture);
		degreeCourse.setCourse(csavedCourse);		
		degreeCourseService.addDegreeCourseLecture(degreeCourse);
		
		mapCourseDegreee.put("action", new String("saved") );
		mapCourseDegreee.put("course", degreeCourse );

		
  	return new  ResponseEntity<Map<String, Object>>(mapCourseDegreee, HttpStatus.OK);
  }
	
	

	public Course addcourse(Course course){
		Map<String, Object> map = new HashMap<>();
		
		if(course.edit) {
		if(courseService.findUnique(course.getName(),course.getId())) {
			throw new NotAcceptableStatusException("course is exsit");
		}else {
			Course editCourse = courseService.findCourseById(course.getId());
			course.setAi(editCourse.getAi());
			Course courseSaved = courseService.addCourse(course);
			
			
			return courseSaved;
		}
	}else {
		Course courseSaved2 = courseService.addCourse(course);
			
		return courseSaved2;
	}
		
	}
	
	@PostMapping("/deleteCourse/{CourseId}")
	public ResponseEntity<Response> deleteCourse(@PathVariable String CourseId){
		courseService.deleteCourse(CourseId);
	return new ResponseEntity<Response>(new Response("deleted Course"),HttpStatus.OK);
			}
	
	@PostMapping("/deleteDegreeCourse/{degreeCourseId}")
	public ResponseEntity<Response> deleteDegreeCourse(@PathVariable String degreeCourseId){
		degreeCourseService.deleteDegreeCourse(degreeCourseId);
	return new ResponseEntity<Response>(new Response("deleted Course"),HttpStatus.OK);
			}
	
	
	
	@PostMapping("/findCourseById/{CourseId}")
	public ResponseEntity<Course> findCourseById(@PathVariable String CourseId){		
	return new ResponseEntity<Course>(courseService.findCourseById(CourseId),HttpStatus.OK);
			}
}
