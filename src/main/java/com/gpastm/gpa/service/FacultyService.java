package com.gpastm.gpa.service;

import java.util.List;

import com.gpastm.gpa.model.Faculty;
import com.gpastm.gpa.model.User;

public interface FacultyService {

	void addFaculty(Faculty faculty);

	void deletefaculty(String facultyId);

	List<Faculty> findAll();

	boolean findUnique(String name, String id);

	Faculty findById(String id);

	
}
