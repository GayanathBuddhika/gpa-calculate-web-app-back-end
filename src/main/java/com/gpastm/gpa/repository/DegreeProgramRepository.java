package com.gpastm.gpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gpastm.gpa.model.DegreeProgram;

public interface DegreeProgramRepository extends JpaRepository<DegreeProgram, String> {

	DegreeProgram findByName(String name);

	List<DegreeProgram> findByDepartment_id(String depId);

}
