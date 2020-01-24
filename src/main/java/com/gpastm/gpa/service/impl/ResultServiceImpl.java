package com.gpastm.gpa.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gpastm.gpa.model.Course;
import com.gpastm.gpa.model.Result;
import com.gpastm.gpa.model.Student;
import com.gpastm.gpa.model.StudentCourse;
import com.gpastm.gpa.repository.ResultRepository;
import com.gpastm.gpa.service.CourseService;
import com.gpastm.gpa.service.ResultService;
import com.gpastm.gpa.service.StudentCourseService;
import com.gpastm.gpa.service.StudentService;

import modelConverter.ResultConverter;
import readCsv.ReadStudentCsvFile;

@Service
@Transactional
public class ResultServiceImpl implements ResultService {
	
	@Autowired
	ResultRepository reultRepository;
	
	@Autowired
	StudentService studentService; 
	
	@Autowired
	CourseService courseService; 
	
	@Autowired
	StudentCourseService studentCourseService; 
	
	@Override
	public List<Result> findAll() {
		// TODO Auto-generated method stub
		return reultRepository.findAll();
	}

	@Override
	public void addResult(Result result,String studentCourseId) {
		// TODO Auto-generated method stub
		
		StudentCourse studentCourse = studentCourseService.findStudentCourseById(studentCourseId);	 
	    result.studentCourse=studentCourse;
		reultRepository.save(result);
	}

	@Override
	public void deleteResult(String resultId) {
		// TODO Auto-generated method stub
		reultRepository.deleteById(resultId);
	}

	@Override
	public List<ResultConverter> addResultBycsvFile(MultipartFile file,String examDate, String courseId)
			throws IOException {
		// TODO Auto-generated method stub
		
		List<ResultConverter> resultList = ReadStudentCsvFile.read(ResultConverter.class,file.getInputStream());
		List<Result> results = new ArrayList<Result>();
		Course course = courseService.findCourseById(courseId);
		
		for(ResultConverter resultConvret : resultList ) {
			
			//Student student = studentService.findstudentByEpnumber(resultConvret.getEpnumber());	
			StudentCourse studentCourse = studentCourseService.findStudentCourseByepNumberCourseId(resultConvret.getEpnumber(), courseId);
			
		        Result result = new Result();
				result.result = resultConvret.getResult();
//				result.student = student;
//				result.course = course;
				result.studentCourse = studentCourse;
				result.examDate =examDate;
				
				results.add(result);
					
		}
		
		
		SaveResultList(results);
		
		return resultList;
	}
	
	public void SaveResultList(List<Result> results) {
		
		reultRepository.saveAll(results).forEach(results::add);
	}

	@Override
	public List<Result> findResultByepNumber(String epNumber) {
		// TODO Auto-generated method stub
		return reultRepository.findAllByStudent_EpNumber(epNumber);
	}

}
