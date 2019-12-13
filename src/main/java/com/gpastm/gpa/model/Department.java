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
public class Department {

	@Id
	public String id = UUID.randomUUID().toString();
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int ai;
	
	public String name;
	
	@ManyToOne
	@JoinColumn(name="faculty_id")
	public Faculty faculty;
}
