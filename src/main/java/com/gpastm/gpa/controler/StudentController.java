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


import com.gpastm.gpa.model.Response;
import com.gpastm.gpa.model.Student;

import com.gpastm.gpa.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	StudentService studentService; 
	
	@GetMapping("/findAllstudent")
	public ResponseEntity<List<Student>> findAllStudent(){
		return new ResponseEntity<List<Student>>(studentService.findAll(), HttpStatus.OK);
	}

	
	@PostMapping("/addStudent")
    public ResponseEntity<Response> addStudent(@RequestBody Student student){
		studentService.addCourse(student);
    	return new ResponseEntity<Response>(new Response("add student") , HttpStatus.OK);
    }
	
	@PostMapping("/deleteStudent/{studentId}")
	public ResponseEntity<Response> deleteStudent(@PathVariable String studentId){
		studentService.deleteStudent(studentId);
	return new ResponseEntity<Response>(new Response("deleted Course"),HttpStatus.OK);
			}

}
