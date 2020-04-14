package com.gpastm.gpa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gpastm.gpa.model.User;

import Enam.Role;


public interface UserRepository extends JpaRepository<User, String> {



	boolean existsByEmail(String email);

	Optional<User> findByEmail(String email);

	List<User> findAllByDepartment_idAndRole(String depId, String lecture);

	



}
