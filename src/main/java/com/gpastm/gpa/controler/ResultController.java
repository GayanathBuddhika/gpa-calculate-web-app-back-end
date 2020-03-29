
package com.gpastm.gpa.controler;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.gpastm.gpa.model.DegreeCourse;
import com.gpastm.gpa.model.Response;
import com.gpastm.gpa.model.Result;
import com.gpastm.gpa.model.StudentCourse;
import com.gpastm.gpa.service.DegreeCourseService;
import com.gpastm.gpa.service.ResultService;
import com.gpastm.gpa.service.StudentCourseService;
import com.gpastm.gpa.service.StudentService;

import modelConverter.ResultConverter;

@Controller
//@CrossOrigin(origins = "http://localhost:4200")  
@RequestMapping("/result")
public class ResultController {

	@Autowired
	ResultService resultService; 
	
	@Autowired
	StudentService studentService; 
	
	@Autowired
	StudentCourseService studentCourseService;
	
	@Autowired
	DegreeCourseService degreeCourseService;
	
	@GetMapping("/findAllResult")
	public ResponseEntity<List<Result>> findAllResult(){
		return new ResponseEntity<List<Result>>(resultService.findAll(), HttpStatus.OK);
	}

	
	@PostMapping("/addResult")
    public ResponseEntity<Response> addResult(@RequestBody Result result, @RequestParam("studentCourseId") String studentCourseId){
		resultService.addResult(result, studentCourseId);
		
    	return new ResponseEntity<Response>(new Response("add Result") , HttpStatus.OK);
    }
	
	@PostMapping("/deleteResult/{ResultId}")
	public ResponseEntity<Response> deleteResult(@PathVariable String ResultId){
		resultService.deleteResult(ResultId);
	return new ResponseEntity<Response>(new Response("deleted Result"),HttpStatus.OK);
			}
	

	@PostMapping(value = "/saveCsvFile", consumes = "multipart/form-data")
	public ResponseEntity<List<ResultConverter>> addResultByCsvFile (
			@RequestParam("file") MultipartFile file,
			@RequestParam("examDate") String examDate,
			@RequestParam("courseId") String courseId)  throws IOException {
		
		return new ResponseEntity<List<ResultConverter>>(resultService.addResultBycsvFile(file,examDate,courseId),HttpStatus.OK);
	}
	

	@GetMapping("/findReultByEpNum/{epNumber}")
	public ResponseEntity<List<Result>> findResultByStudentId(@PathVariable String epNumber){
		
		return new ResponseEntity<List<Result>>(resultService.findResultByepNumber(epNumber), HttpStatus.OK);
	}
	
	@GetMapping("/findbatch")
	public ResponseEntity<List<String>> findBatch(){
		System.out.println("#################333" + studentService.findAllbatch());
		return new ResponseEntity<List<String>> (studentService.findAllbatch(), HttpStatus.OK);
	}
	
	@GetMapping("/findcourse/{courseId}")
	public ResponseEntity<List<DegreeCourse>> findCourse(@PathVariable String courseId){
//		System.out.println("#################333" + studentService.findAllbatch());
		return new ResponseEntity<List<DegreeCourse>> (degreeCourseService.findDegreeCourse(courseId), HttpStatus.OK);
	}
//	 parameters = parameters.set('courseId', courseId).set('batch', batch);
	@GetMapping("/findStudent")
	public ResponseEntity<List<StudentCourse>> findCourse( @RequestParam("courseId") String courseId , @RequestParam("batch") String batch){
//		System.out.println("#################333" + studentService.findAllbatch());
		return new ResponseEntity<List<StudentCourse>> (studentCourseService.findstudents(courseId,batch), HttpStatus.OK);
	}
	
}
