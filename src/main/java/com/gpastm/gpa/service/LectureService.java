package com.gpastm.gpa.service;

import java.util.List;

import com.gpastm.gpa.model.Lecture;

public interface LectureService {

	List<Lecture> findAll();

	void addLectuert(Lecture lecture);

	void deleteLectuert(String lectuerId);

}
