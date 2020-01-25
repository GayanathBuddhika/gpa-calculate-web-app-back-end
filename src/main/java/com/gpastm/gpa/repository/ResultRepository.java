package com.gpastm.gpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gpastm.gpa.model.Result;

public interface ResultRepository extends JpaRepository<Result, String>{

	//List<Result> findAllByStudent_EpNumber(String epNumber);

}
