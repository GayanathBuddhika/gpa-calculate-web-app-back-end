package com.gpastm.gpa.service;

import java.util.List;

import com.gpastm.gpa.model.User;

import Enam.Role;

public interface UserService {

	List<User> findAll();

	void adduser(User user);

	void deleteUser(String userId);

	boolean findUnique(String email, String id);

	User findById(String id);

	boolean findExsit(String email);

	User findLecturebyId(String lectureId);

	List<User> findLectureByDepId(String depId);

}
