package com.gpastm.gpa.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
