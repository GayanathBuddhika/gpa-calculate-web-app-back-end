package com.gpastm.gpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gpastm.gpa.model.User;

import Enam.Role;


public interface UserRepository extends JpaRepository<User, String> {



	boolean existsByEmail(String email);

	User findByEmail(String email);

	List<User> findAllByDepartment_idAndRole(String depId, String lecture);

	



}
