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
import org.springframework.web.server.NotAcceptableStatusException;


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
    public ResponseEntity<Map<String, Object>> addLectuer(@RequestBody Lecture lecture){
		Map<String, Object> map = new HashMap<>();

		
		if(lecture.edit) {
		if(lectureService.findUnique(lecture.getName(),lecture.getId())) {
			throw new NotAcceptableStatusException("lecture is exsit");
		}else {
			Lecture editLecture = lectureService.findLecturebyId(lecture.getId());
			lecture.setAi(editLecture.getAi());
			Lecture  lectureSaved = lectureService.addLectuert(lecture);
			map.put("action", new String("saved"));
			map.put("lecture", lectureSaved);		
			
			return new ResponseEntity<Map<String, Object>>(map , HttpStatus.OK);
		}
	}else {
		Lecture lectureSaved2 = lectureService.addLectuert(lecture);
		map.put("action", new String("saved"));
		map.put("lecture", lectureSaved2);		
		return new ResponseEntity<Map<String, Object>>(map , HttpStatus.OK);
	}
		
    	
    }
	
	@PostMapping("/deleteLecture/{lectuerId}")
	public ResponseEntity<Response> deleteLecture(@PathVariable String lectuerId){
		lectureService.deleteLectuert(lectuerId);
	return new ResponseEntity<Response>(new Response("deleted Lectuer"),HttpStatus.OK);
			}
	
}


//@PostMapping("/addDegreeProgram")
//public ResponseEntity<Map<String, Object>> addDegreeProram(@RequestBody DegreeProgram degreeProgram){
//	Map<String, Object> map = new HashMap<>();
//	
//	if(degreeProgram.edit) {
//	if(degreeProgramService.findUnique(degreeProgram.getName(),degreeProgram.getId())) {
//		throw new NotAcceptableStatusException("DegreeProgram is exsit");
//	}else {
//		DegreeProgram editDegreeProgram = degreeProgramService.findDegreeProgramById(degreeProgram.getId());
//		degreeProgram.setAi(editDegreeProgram.getAi());
//		DegreeProgram degreeProgramSaved = degreeProgramService.addDegreeProgram(degreeProgram);
//		map.put("action", new String("saved"));
//		map.put("degreeProgram", degreeProgramSaved);		
//		
//		return new ResponseEntity<Map<String, Object>>(map , HttpStatus.OK);
//	}
//}else {
//	DegreeProgram degreeProgramSaved2 = degreeProgramService.addDegreeProgram(degreeProgram);
//	map.put("action", new String("saved"));
//	map.put("degreeProgram", degreeProgramSaved2);		
//	return new ResponseEntity<Map<String, Object>>(map , HttpStatus.OK);
//}
//	
//}