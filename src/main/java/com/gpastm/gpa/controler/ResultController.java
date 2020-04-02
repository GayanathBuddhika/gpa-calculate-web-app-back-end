
package com.gpastm.gpa.controler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Map<String, Object>> addResult(@RequestBody List<Result> resultList, @RequestParam("edit2") String edit){
		Map<String, Object> map = new HashMap<>();
		if(edit.equals("true")){
			List<Result> editResult = new ArrayList<>();
			for (Result result2 : resultList) {
				Result dummyResult = resultService.findResultById(result2.getId());
				result2.setAi(dummyResult.getAi());
				editResult.add(result2);
			}
			resultService.addResultList(resultList);
            
			map.put("action", new String("saved"));
			map.put("result", resultList);	
			return new ResponseEntity<Map<String, Object>>(map , HttpStatus.OK);
		}else {			
			resultService.addResultList(resultList);			
			map.put("action", new String("saved"));			
			map.put("result", resultList);				
			return new ResponseEntity<Map<String, Object>>(map , HttpStatus.OK);
		}		

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
	
	@GetMapping("/findExamName")
	public ResponseEntity<List<String>> findExamName(){
		System.out.println("tttttttttt" + resultService.findAllEaxmName());
		return new ResponseEntity<List<String>> (resultService.findAllEaxmName(), HttpStatus.OK);
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
	
	@GetMapping("/findForLecture")
	public ResponseEntity<List<Result>> findResultForLecture(@RequestParam("lectureId") String lectureId , @RequestParam("examName") String examName) {
//		 String lecid ="67a5f5a9-206e-42b6-9f4f-b2686f8be8a2";
//		 String exam = "example";
		return new ResponseEntity<List<Result>> (resultService.findresultForLecture(lectureId,examName), HttpStatus.OK);
	}
	
	@PostMapping("/lecAppreoved")
    public void lectureApproved(@RequestBody List<Result> resultList){	
		List<Result> resultListForSave= new ArrayList<>();
		for (Result result : resultList) {
			Result re = resultService.findResultById(result.getId());
		    re.setLecApproval(true);
		    resultListForSave.add(re);
		}
		
		resultService.addResultList(resultListForSave);;
    }
	@PostMapping("/hedAppreoved")
    public void hesdApproved(@RequestBody List<Result> resultList){	
		List<Result> resultListForSave= new ArrayList<>();
		for (Result result : resultList) {
			Result re = resultService.findResultById(result.getId());
		    re.setDepHedApproval(true);
		    resultListForSave.add(re);
		}
		
		resultService.addResultList(resultListForSave);
		
    }
	@PostMapping("/deenAppreoved")
    public void deenApproved(@RequestBody List<Result> resultList){	
		List<Result> resultListForSave= new ArrayList<>();
		for (Result result : resultList) {
			Result re = resultService.findResultById(result.getId());
		    re.setDeenApproval(true);
		    resultListForSave.add(re);
		}
		
		resultService.addResultList(resultListForSave);
    }
	
	
}
