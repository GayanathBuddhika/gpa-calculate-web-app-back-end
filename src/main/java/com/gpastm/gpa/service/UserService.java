package com.gpastm.gpa.service;

import java.util.List;
import java.util.Optional;

import com.gpastm.gpa.model.User;

import Enam.Role;

public interface UserService {

	List<User> findAll();

	User adduser(User user);

	void deleteUser(String userId);

	boolean findUnique(String email, String id);

	User findById(String id);

	boolean findExsit(String email);

	User findLecturebyId(String lectureId);

	List<User> findLectureByDepId(String depId);
	
	Optional<User> findByEmail(String email);

}
