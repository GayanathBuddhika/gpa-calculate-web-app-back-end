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
public class StudentCourse {
	@Id
    public String id = UUID.randomUUID().toString();
    
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    public int ai;
 
	@ManyToOne
	@JoinColumn(name="student_id")
	public Student student;
	
	@ManyToOne
	@JoinColumn(name="course_id")
	public Course course;

}
