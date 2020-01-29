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
import com.gpastm.gpa.model.Response;
import com.gpastm.gpa.service.CourseService;
import com.gpastm.gpa.service.DegreeCourseService;

import modelConverter.DegreeLectureCourse;


@Controller
@RequestMapping("/course")
public class CourseController {

	
	@Autowired
	CourseService courseService; 
	
	
	@Autowired
	DegreeCourseService degreeCourseService;
	
	@GetMapping("/findAllcourse")
	public ResponseEntity<List<Course>> findAllCourse(){
		return new ResponseEntity<List<Course>>(courseService.findAll(), HttpStatus.OK);
	}

	
//	@PostMapping("/addCourse")
//    public ResponseEntity<Response> addCourse(
//    		@RequestBody Course course,
//    		@RequestParam("degreeProgramId") String degreeProgramId,
//    		@RequestParam("lecturId") String lectureId){
//		
//		
//		courseService.addCourse(course);
//		degreeCourseService.addDegreeCourse(course.id,degreeProgramId,lectureId );
//		
//		
//    	return new ResponseEntity<Response>(new Response("add Course") , HttpStatus.OK);
//    }
//	
	
	@PostMapping("/addCourse")
    public  ResponseEntity<Map<String, Object>> addCourse(
  		@RequestBody DegreeLectureCourse degreeLectureCourse){
		Map<String, Object> mapCourse = new HashMap<>();
       
		mapCourse = addcourse(degreeLectureCourse.getCourse());
		
		DegreeCourse degreeCourse = new DegreeCourse();
		degreeCourse.setDegreeProgram(degreeLectureCourse.getDegree());
		degreeCourse.setLecture(degreeLectureCourse.getLectuer());
		degreeCourse.setCourse((Course)mapCourse.get("course"));
		
		degreeCourseService.addDegreeCourseLecture(degreeCourse);
		
//		courseService.addCourse(course);
//		degreeCourseService.addDegreeCourse(course.id,degreeProgramId,lectureId );
		
		
  	return new  ResponseEntity<Map<String, Object>>(mapCourse, HttpStatus.OK);
  }
	
	

	public Map<String, Object> addcourse(Course course){
		Map<String, Object> map = new HashMap<>();
		
		if(course.edit) {
		if(courseService.findUnique(course.getName(),course.getId())) {
			throw new NotAcceptableStatusException("course is exsit");
		}else {
			Course editCourse = courseService.findCourseById(course.getId());
			course.setAi(editCourse.getAi());
			Course courseSaved = courseService.addCourse(course);
			map.put("action", new String("saved"));
			map.put("course", courseSaved);		
			
			return map;
		}
	}else {
		Course courseSaved2 = courseService.addCourse(course);
		map.put("action", new String("saved"));
		map.put("course", courseSaved2);		
		return map;
	}
		
	}
	
	@PostMapping("/deleteCourse/{CourseId}")
	public ResponseEntity<Response> deleteCourse(@PathVariable String CourseId){
		courseService.deleteCourse(CourseId);
	return new ResponseEntity<Response>(new Response("deleted Course"),HttpStatus.OK);
			}
	
	@PostMapping("/findCourseById/{CourseId}")
	public ResponseEntity<Course> findCourseById(@PathVariable String CourseId){
		
	return new ResponseEntity<Course>(courseService.findCourseById(CourseId),HttpStatus.OK);
			}
}
