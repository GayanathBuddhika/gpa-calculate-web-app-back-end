package com.gpastm.gpa.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gpastm.gpa.model.Course;

import com.gpastm.gpa.model.Response;
import com.gpastm.gpa.service.CourseService;


@Controller
@RequestMapping("/Course")
public class CourseController {

	
	@Autowired
	CourseService courseService; 
	
	@GetMapping("/findAllcourse")
	public ResponseEntity<List<Course>> findAllCourse(){
		return new ResponseEntity<List<Course>>(courseService.findAll(), HttpStatus.OK);
	}

	
	@PostMapping("/addCourse")
    public ResponseEntity<Response> addLectuer(@RequestBody Course course){
		courseService.addCourse(course);
    	return new ResponseEntity<Response>(new Response("add Course") , HttpStatus.OK);
    }
	
	@PostMapping("/deleteCourse/{CourseId}")
	public ResponseEntity<Response> deleteCourse(@PathVariable String CourseId){
		courseService.deleteCourse(CourseId);
	return new ResponseEntity<Response>(new Response("deleted Course"),HttpStatus.OK);
			}
}
