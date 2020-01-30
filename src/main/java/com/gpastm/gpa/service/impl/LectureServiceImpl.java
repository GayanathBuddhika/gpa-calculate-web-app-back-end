package com.gpastm.gpa.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.gpastm.gpa.model.Lecture;
import com.gpastm.gpa.repository.LectureRepository;
import com.gpastm.gpa.service.LectureService;

@Service
@Transactional
public class LectureServiceImpl implements LectureService {
	
	@Autowired
	LectureRepository lectureRepository;

	@Override
	public List<Lecture> findAll() {
		// TODO Auto-generated method stub
		return lectureRepository.findAll();
	}

	@Override
	public Lecture addLectuert(Lecture lecture) {
		// TODO Auto-generated method stub
		return lectureRepository.save(lecture);
	}

	@Override
	public void deleteLectuert(String lectuerId) {
		// TODO Auto-generated method stub
		
		lectureRepository.deleteById(lectuerId);
	}

	@Override
	public Lecture findLecturebyId(String lectureId) {
		// TODO Auto-generated method stub
		return lectureRepository.getOne(lectureId);
	}

	@Override
	public boolean findUnique(String name, String id) {
		Lecture lecture = lectureRepository.findByName(name);
		return lecture != null && !lecture.getId().equals(id);
	}

	@Override
	public List<Lecture> DindLectuerByDepId(String lectureId) {
		// TODO Auto-generated method stub
		return lectureRepository.findByDepartment_id(lectureId);
	}

}
