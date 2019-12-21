package com.gpastm.gpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gpastm.gpa.model.Student;

public interface StudentRepository extends JpaRepository<Student, String> {

}
