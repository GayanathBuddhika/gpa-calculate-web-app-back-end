package com.gpastm.gpa.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.transform.ResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gpastm.gpa.model.Course;
import com.gpastm.gpa.model.Result;
import com.gpastm.gpa.model.Student;
import com.gpastm.gpa.repository.ResultRepository;
import com.gpastm.gpa.service.CourseService;
import com.gpastm.gpa.service.ResultService;
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
	
	@Override
	public List<Result> findAll() {
		// TODO Auto-generated method stub
		return reultRepository.findAll();
	}

	@Override
	public void addResult(Result result,String courseId) {
		// TODO Auto-generated method stub
		
		Course course =courseService.findCourseById(courseId);
		
		result.course =course;
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
			
			Student student = studentService.findstudentByEpnumber(resultConvret.getEpnumber());			
			
		        Result result = new Result();
				result.result = resultConvret.getResult();
				result.student = student;
				result.course = course;
				result.examDate =examDate;
				results.add(result);
					
		}
		
		
		SaveResultList(results);
		
		return resultList;
	}
	
	public void SaveResultList(List<Result> results) {
		
		reultRepository.saveAll(results).forEach(results::add);
	}

}
