package com.gpastm.gpa.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpastm.gpa.model.DegreeProgram;
import com.gpastm.gpa.model.Department;
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

	@Override
	public List<DegreeProgram> findAll() {
		// TODO Auto-generated method stub
		return degreeProgramRepository.findAll();
	}

	@Override
	public boolean findUnique(String name, String id) {
		// TODO Auto-generated method stub
		DegreeProgram degreeProgram = degreeProgramRepository.findByName(name); 
		
		return degreeProgram !=null && degreeProgram.getId().equals(id) ;
	}

	@Override
	public DegreeProgram addDegreeProgram(DegreeProgram degreeProgram) {
		// TODO Auto-generated method stub
		return degreeProgramRepository.save(degreeProgram);
	}

	@Override
	public void deleteDegreeProgram(String degreeProgramId) {
		// TODO Auto-generated method stub
		degreeProgramRepository.deleteById(degreeProgramId);
	}

	@Override
	public List<DegreeProgram> findByDepartmentId(String depId) {
		// TODO Auto-generated method stub
		return degreeProgramRepository.findByDepartment_id(depId);
	}

}
