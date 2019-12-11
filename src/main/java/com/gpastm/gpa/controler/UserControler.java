package com.gpastm.gpa.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gpastm.gpa.model.Response;
import com.gpastm.gpa.model.User;
import com.gpastm.gpa.service.UserService;


@Controller
@RequestMapping("/user")
public class UserControler {
	
	@Autowired
	UserService userService; 
	
	@GetMapping("/findAllUsers")
	public ResponseEntity<List<User>> findAllUsers(){
		return new ResponseEntity<List<User>>(userService.findAll(), HttpStatus.OK);
	}

	@PostMapping("/addUser")
    public ResponseEntity<Response> addUser(@RequestBody User user){
    	userService.adduser(user);
    	return new ResponseEntity<Response>(new Response("add user") , HttpStatus.OK);
    }
}
