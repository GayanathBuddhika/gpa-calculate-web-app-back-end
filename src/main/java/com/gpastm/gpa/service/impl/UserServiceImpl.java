package com.gpastm.gpa.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpastm.gpa.model.User;
import com.gpastm.gpa.repository.UserRepository;
import com.gpastm.gpa.service.UserService;

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

}
