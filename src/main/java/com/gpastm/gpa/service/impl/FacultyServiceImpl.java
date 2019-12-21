package com.gpastm.gpa.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpastm.gpa.model.Faculty;
import com.gpastm.gpa.model.User;
import com.gpastm.gpa.repository.FacultyRepository;
import com.gpastm.gpa.service.FacultyService;

@Service
@Transactional
public class FacultyServiceImpl implements FacultyService {
	
	@Autowired
	FacultyRepository facultyRepository;

	@Override
	public void addFaculty(Faculty faculty) {
		// TODO Auto-generated method stub
		facultyRepository.save(faculty);
		
	}

	@Override
	public void deletefaculty(String facultyId) {
		// TODO Auto-generated method stub
		facultyRepository.deleteById(facultyId);
		
	}

	@Override
	public List<Faculty> findAll() {
		// TODO Auto-generated method stub
		return facultyRepository.findAll();
	}

}
