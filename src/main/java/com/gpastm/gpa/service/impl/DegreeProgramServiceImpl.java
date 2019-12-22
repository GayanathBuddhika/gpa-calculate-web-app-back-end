package com.gpastm.gpa.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpastm.gpa.model.DegreeProgram;
import com.gpastm.gpa.repository.DegreeProgramRepository;
import com.gpastm.gpa.service.DegreeProgramService;

@Service
@Transactional
public class DegreeProgramServiceImpl implements DegreeProgramService{
	
	@Autowired
	DegreeProgramRepository degreeProgramRepository;

	@Override
	public DegreeProgram findDegreeProgramById(String degreeProgramId) {
		// TODO Auto-generated method stub
		return degreeProgramRepository.getOne(degreeProgramId);
	}

}
