package com.gpastm.gpa.controler;

import java.util.ArrayList;
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

import com.gpastm.gpa.model.Response;
import com.gpastm.gpa.model.Student;
import com.gpastm.gpa.model.User;
import com.gpastm.gpa.service.UserService;

import Enam.Role;


@Controller
@RequestMapping("/user")
public class UserControler {
	
	@Autowired
	UserService userService; 
	
	@GetMapping("/findAllUsers")
	public ResponseEntity<List<User>> findAllUsers(){
		return new ResponseEntity<List<User>>(userService.findAll(), HttpStatus.OK);
	}

	@GetMapping("/findAllRole")
	public ResponseEntity<List<Role>> findAllRole(){
	
		List<Role> roles = new ArrayList<Role>(new User().getRoles());
		
	   
		return new ResponseEntity<List<Role>>(roles, HttpStatus.OK);
	}

	
	@PostMapping("/addUser")
    public ResponseEntity<Map<String, Object>> addUser(@RequestBody User user){
		Map<String, Object> map = new HashMap<>();
		if(user.getEdit()) {
			if(userService.findUnique(user.getEmail(), user.getId())) {
				throw new NotAcceptableStatusException("User is exsit by this email");
			}else {
				User editUser = userService.findById(user.getId());
				user.setAi(editUser.getAi());
				userService.adduser(user);
				map.put("action", new String("saved"));
				map.put("user", user);
				return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
				
			}
		}else {
			if(userService.findExsit(user.getEmail())){
				throw new NotAcceptableStatusException("that email is exsit");
			}else {
				userService.adduser(user);
				map.put("action", new String("saved"));
				map.put("user", user);
				return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
			}
			
		}
    	
    }

	@PostMapping("/deleteUser/{userId}")
	public ResponseEntity<Response> deleteUser(@PathVariable String userId){
		userService.deleteUser(userId);
	return new ResponseEntity<Response>(new Response("deleted user"),HttpStatus.OK);
			}
	
	@GetMapping("/findAllLecture/{depId}")
	public ResponseEntity<List<User>> findAllLectureByDepId(@PathVariable String depId){
	
		
		
	   
		return new ResponseEntity<List<User>>(userService.findLectureByDepId(depId), HttpStatus.OK);
	}

}
