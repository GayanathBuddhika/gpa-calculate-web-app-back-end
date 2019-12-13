package com.gpastm.gpa.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Lecture {

	@Id
	public String id = UUID.randomUUID().toString();
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int ai;
	
	public String registrationNumber;
	
	public String name;
	
}
