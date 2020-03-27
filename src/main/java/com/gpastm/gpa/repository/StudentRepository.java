package com.gpastm.gpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gpastm.gpa.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

	Student findByEpNumber(String epnumber);

	Student findByStudentName(String studentName);

	List<Student> findByDepartment_id(String depId);
    
	@Query("select distinct s.batch from Student s")
	List<String> allbatch();

}
