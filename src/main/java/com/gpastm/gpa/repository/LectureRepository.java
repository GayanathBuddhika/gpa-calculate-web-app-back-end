package com.gpastm.gpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gpastm.gpa.model.Lecture;

public interface LectureRepository extends JpaRepository<Lecture, String> {

}
