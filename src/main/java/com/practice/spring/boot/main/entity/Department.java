package com.practice.spring.boot.main.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table (name="departments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Department 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long departmentId;
	
	@Column (nullable = false)
	private String hodName;
	
	@Column(nullable = false,unique = true)
	private String emailId;
	
	@Column(nullable = false)
	private	String departmentName;
	
	
	

}
