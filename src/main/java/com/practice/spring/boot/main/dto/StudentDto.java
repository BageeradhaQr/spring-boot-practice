package com.practice.spring.boot.main.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StudentDto {
	
	
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private String emailAddress;

}
