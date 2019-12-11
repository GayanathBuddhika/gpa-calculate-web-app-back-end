package com.gpastm.gpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gpastm.gpa.model.User;


public interface UserRepository extends JpaRepository<User, String> {

}
