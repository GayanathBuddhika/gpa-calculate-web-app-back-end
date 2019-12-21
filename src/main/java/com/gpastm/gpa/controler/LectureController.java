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


import com.gpastm.gpa.model.Lecture;
import com.gpastm.gpa.model.Response;

import com.gpastm.gpa.service.LectureService;

@Controller
@RequestMapping("/Lecture")
public class LectureController {

	@Autowired
	LectureService lectureService; 
	
	@GetMapping("/findAllLecture")
	public ResponseEntity<List<Lecture>> findAllLecture(){
		return new ResponseEntity<List<Lecture>>(lectureService.findAll(), HttpStatus.OK);
	}

	
	@PostMapping("/addLectuer")
    public ResponseEntity<Response> addLectuer(@RequestBody Lecture lecture){
		lectureService.addLectuert(lecture);
    	return new ResponseEntity<Response>(new Response("add Lectuer") , HttpStatus.OK);
    }
	
	@PostMapping("/deleteLecture/{lectuerId}")
	public ResponseEntity<Response> deleteLecture(@PathVariable String lectuerId){
		lectureService.deleteLectuert(lectuerId);
	return new ResponseEntity<Response>(new Response("deleted Lectuer"),HttpStatus.OK);
			}
	
}
