package com.gpastm.gpa.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class DegreeCourse {

	@Id
	public String id = UUID.randomUUID().toString();
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int ai;
	
	@ManyToOne
	@JoinColumn(name="degree_program_id")
	public DegreeProgram degreeProgram;
	
	@ManyToOne
	@JoinColumn(name="course_id")	
	public Course course;
	
	@ManyToOne
	@JoinColumn(name="lecture_id")
	public Lecture lecture;
}
