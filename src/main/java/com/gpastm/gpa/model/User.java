package com.gpastm.gpa.model;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import Enam.Role;
import lombok.Data;

@Entity
@Data
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class User {

	@Id
    private String id = UUID.randomUUID().toString();
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ai;	
	private String email;
	private String role;
	private String name;	
	private String phoneNumber;
	
	@ManyToOne
	@JoinColumn(name="department_id")
	private Department department;
	
	@ManyToOne
	@JoinColumn(name="faculty_id")
	private Faculty faculty;
  
	
	@Transient
	@JsonIgnore
	private List<Role> roles= Arrays.asList(Role.values());
	
	@Transient
	private Boolean edit;

}
