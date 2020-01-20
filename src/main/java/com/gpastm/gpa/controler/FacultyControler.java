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

import com.gpastm.gpa.model.Faculty;
import com.gpastm.gpa.model.Response;
import com.gpastm.gpa.service.FacultyService;

@Controller
@RequestMapping("/faculty")
public class FacultyControler {
	
	@Autowired
	FacultyService facultyService;
	
	@GetMapping("/findAllFaculty")
	public ResponseEntity<List<Faculty>> findAllUsers(){
		return new ResponseEntity<List<Faculty>>(facultyService.findAll(), HttpStatus.OK);
	}

	
	@PostMapping("/addFaculty")
    public ResponseEntity<Map<String, Object>> addUser(@RequestBody Faculty faculty){
		Map<String, Object> map = new HashMap<>();
		if(faculty.edit) {
			if(facultyService.findUnique(faculty.getName(),faculty.getId())) {
				throw new NotAcceptableStatusException("company is exsit");
			}else {
				Faculty editFaculty = facultyService.findById(faculty.getId());
				faculty.setAi(editFaculty.getAi());
				map.put("action", new String("saved"));
				map.put("faculty", faculty);		
				facultyService.addFaculty(faculty);
				return new ResponseEntity<Map<String, Object>>(map , HttpStatus.OK);
			}
		}else {
			
			map.put("action", new String("saved"));
			map.put("faculty", faculty);		
			facultyService.addFaculty(faculty);
	    	return new ResponseEntity<Map<String, Object>>(map , HttpStatus.OK);
		}
		
    }
	
	@PostMapping("/deleteFaculty/{facultyId}")
	public ResponseEntity<Response> deleteUser(@PathVariable String facultyId){
		facultyService.deletefaculty(facultyId);
	return new ResponseEntity<Response>(new Response("deleted faculty"),HttpStatus.OK);
			}

}
