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
			map.put("faculty", degreeProgramSaved);		
			
			return new ResponseEntity<Map<String, Object>>(map , HttpStatus.OK);
		}
	}else {
		DegreeProgram degreeProgramSaved2 = degreeProgramService.addDegreeProgram(degreeProgram);
		map.put("action", new String("saved"));
		map.put("faculty", degreeProgramSaved2);		
		return new ResponseEntity<Map<String, Object>>(map , HttpStatus.OK);
	}
		//return new ResponseEntity<Response>(new Response("add Department") , HttpStatus.OK);
	}
	
	
	@PostMapping("/deleteDegreeProgram/{degreeProgramId}")
	public ResponseEntity<Response> deleteDegreeProgram(@PathVariable String degreeProgramId){
		degreeProgramService.deleteDegreeProgram(degreeProgramId);
	return new ResponseEntity<Response>(new Response("deleted department"),HttpStatus.OK);
			}
	

}


//@Autowired
//DepartmentService departmentService; 
//
//@GetMapping("/findAllDepartment")
//public ResponseEntity<List<Department>> findAllDepartment(){
//	return new ResponseEntity<List<Department>>(departmentService.findAll(), HttpStatus.OK);
//}
//
//
//@PostMapping("/addDepartment")
//public ResponseEntity<Map<String, Object>> addDepartment(@RequestBody Department department){
//	Map<String, Object> map = new HashMap<>();
//	departmentService.addDepartment(department);
//	if(department.edit) {
//	if(departmentService.findUnique(department.getName(),department.getId())) {
//		throw new NotAcceptableStatusException("company is exsit");
//	}else {
//		Department editdepartment = departmentService.findDepartmentById(department.getId());
//		department.setAi(editdepartment.getAi());
//		map.put("action", new String("saved"));
//		map.put("faculty", department);		
//		departmentService.addDepartment(department);
//		return new ResponseEntity<Map<String, Object>>(map , HttpStatus.OK);
//	}
//}else {
//	
//	map.put("action", new String("saved"));
//	map.put("faculty", department);		
//	departmentService.addDepartment(department);
//	return new ResponseEntity<Map<String, Object>>(map , HttpStatus.OK);
//}
//	//return new ResponseEntity<Response>(new Response("add Department") , HttpStatus.OK);
//}
//
//@PostMapping("/deleteDepartment/{departmentId}")
//public ResponseEntity<Response> deleteDepartment(@PathVariable String departmentId){
//	departmentService.deleteDepartment(departmentId);
//return new ResponseEntity<Response>(new Response("deleted department"),HttpStatus.OK);
//		}
//}