package com.gpastm.gpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gpastm.gpa.model.Lecture;

public interface LectureRepository extends JpaRepository<Lecture, String> {

	Lecture findByName(String name);

	List<Lecture> findByDepartment_id(String lectureId);

}
