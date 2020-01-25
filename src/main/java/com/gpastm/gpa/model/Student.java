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
public class Student {
	
	@Id
	public String id = UUID.randomUUID().toString();
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int ai;
	
	public String epNumber;
	
    public String registrationNumber;
    
    public String batch;
    
    public String studentName;
    
    public int degreeProYear;
    
    @ManyToOne
	@JoinColumn(name="department_id")
    public Department department;
    
   // --- that edit is not property in the database table. but it not gives a error
	@Transient
	public boolean edit;

    
}