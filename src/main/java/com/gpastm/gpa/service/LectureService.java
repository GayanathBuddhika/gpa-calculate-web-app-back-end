package com.gpastm.gpa.service;

import java.util.List;

import com.gpastm.gpa.model.Lecture;

public interface LectureService {

	List<Lecture> findAll();

	Lecture addLectuert(Lecture lecture);

	void deleteLectuert(String lectuerId);

	Lecture findLecturebyId(String lectureId);

	boolean findUnique(String name, String id);

}
