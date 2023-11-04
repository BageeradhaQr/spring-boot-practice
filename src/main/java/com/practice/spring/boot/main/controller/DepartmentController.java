package com.practice.spring.boot.main.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.spring.boot.main.entity.Department;

@RestController
@RequestMapping("departments")
public class DepartmentController {
	
	public ResponseEntity<Department> createDepartment(){
		
		return null;
	}

}
