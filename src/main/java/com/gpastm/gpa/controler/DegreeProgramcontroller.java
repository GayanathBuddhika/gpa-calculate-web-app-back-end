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

import com.gpastm.gpa.model.DegreeProgram;
import com.gpastm.gpa.model.Department;
import com.gpastm.gpa.model.Response;
import com.gpastm.gpa.service.DegreeProgramService;
import com.gpastm.gpa.service.DepartmentService;

@Controller
@RequestMapping("/degreeProgram")
public class DegreeProgramcontroller {
	
	@Autowired
	DegreeProgramService degreeProgramService;
	
	@GetMapping("/findAllDegreeProgram")
	public ResponseEntity<List<DegreeProgram>> findAllDegreeProgram(){
		return new ResponseEntity<List<DegreeProgram>>(degreeProgramService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/findAllDegreeProgramByDepId/{depId}")
	public ResponseEntity<List<DegreeProgram>> findAllDegreeProgramByDepId(@PathVariable String depId){
		return new ResponseEntity<List<DegreeProgram>>(degreeProgramService.findByDepartmentId(depId), HttpStatus.OK);
	}
	
	@PostMapping("/addDegreeProgram")
	public ResponseEntity<Map<String, Object>> addDegreeProram(@RequestBody DegreeProgram degreeProgram){
		Map<String, Object> map = new HashMap<>();
		
		if(degreeProgram.edit) {
		if(degreeProgramService.findUnique(degreeProgram.getName(),degreeProgram.getId())) {
			throw new NotAcceptableStatusException("DegreeProgram is exsit");
		}else {
			DegreeProgram editDegreeProgram = degreeProgramService.findDegreeProgramById(degreeProgram.getId());
			degreeProgram.setAi(editDegreeProgram.getAi());
			DegreeProgram degreeProgramSaved = degreeProgramService.addDegreeProgram(degreeProgram);
			map.put("action", new String("saved"));
			map.put("degreeProgram", degreeProgramSaved);		
			
			return new ResponseEntity<Map<String, Object>>(map , HttpStatus.OK);
		}
	}else {
		DegreeProgram degreeProgramSaved2 = degreeProgramService.addDegreeProgram(degreeProgram);
		map.put("action", new String("saved"));
		map.put("degreeProgram", degreeProgramSaved2);		
		return new ResponseEntity<Map<String, Object>>(map , HttpStatus.OK);
	}
		
	}
	
	
	@PostMapping("/deleteDegreeProgram/{degreeProgramId}")
	public ResponseEntity<Response> deleteDegreeProgram(@PathVariable String degreeProgramId){
		degreeProgramService.deleteDegreeProgram(degreeProgramId);
	return new ResponseEntity<Response>(new Response("deleted department"),HttpStatus.OK);
			}
	

}


