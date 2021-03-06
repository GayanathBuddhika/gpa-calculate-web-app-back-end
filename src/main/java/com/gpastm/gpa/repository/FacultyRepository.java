package com.gpastm.gpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gpastm.gpa.model.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, String> {

	Faculty findByName(String name);

}
