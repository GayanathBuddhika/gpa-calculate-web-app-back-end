package com.gpastm.gpa.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.gpastm.gpa.model.User;
import com.gpastm.gpa.repository.UserRepository;
import com.gpastm.gpa.service.UserService;

import Enam.Role;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public void adduser(User user) {
		// TODO Auto-generated method stub
		userRepository.save(user);
	}

	@Override
	public void deleteUser(String userId) {
	 
	  userRepository.deleteById(userId);
		
	}

	@Override
	public boolean findUnique(String email, String id) {
		// TODO Auto-generated method stub
		User user = userRepository.findByEmail(email);
		return user != null && !user.getId().equals(id);
	}

	@Override
	public User findById(String id) {
		// TODO Auto-generated method stub
		return userRepository.getOne(id);
	}

	@Override
	public boolean findExsit(String email) {
		// TODO Auto-generated method stub
		return userRepository.existsByEmail(email);
	}

	@Override
	public User findLecturebyId(String lectureId) {
		// TODO Auto-generated method stub
		return userRepository.getOne(lectureId);
	}

	@Override
	public List<User> findLectureByDepId(String depId) {
		// TODO Auto-generated method stub
		String lecture = "LECTURE";
		return userRepository.findAllByDepartment_idAndRole(depId,lecture);
	}


}
