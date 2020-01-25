package com.gpastm.gpa.service;

import java.util.List;

import com.gpastm.gpa.model.DegreeProgram;
import com.gpastm.gpa.model.Department;

public interface DegreeProgramService {

	DegreeProgram findDegreeProgramById(String degreeProgramId);

	List<DegreeProgram> findAll();

    boolean findUnique(String name, String id);

	DegreeProgram addDegreeProgram(DegreeProgram degreeProgram);

	void deleteDegreeProgram(String degreeProgramId);

}
