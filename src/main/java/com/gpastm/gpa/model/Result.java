package com.gpastm.gpa.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Result {
	
	@Id
    public String id = UUID.randomUUID().toString();
    
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    public int ai;
 
	public String result;
	
	public String examName;
	
	public Boolean depHedApproval;
	public Boolean lecApproval;
	public Boolean deenApproval;
//	@ManyToOne
//	@JoinColumn(name="student_id")
//	public Student student;
//	
//	@ManyToOne
//	@JoinColumn(name="course_id")
//	public Course course;
	
	@ManyToOne
	@JoinColumn(name="student_course")
	public StudentCourse studentCourse;
	
	@Transient
	public Boolean edit;

}
