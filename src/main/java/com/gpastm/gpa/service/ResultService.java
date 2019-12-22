package com.gpastm.gpa.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.gpastm.gpa.model.Result;

import modelConverter.ResultConverter;

public interface ResultService {

	List<Result> findAll();

	void addResult(Result result,String courseId);

	void deleteResult(String resultId);

	List<ResultConverter> addResultBycsvFile(MultipartFile file,String examDate,String courseId)throws IOException;

}
