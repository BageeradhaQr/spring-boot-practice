package com.practice.spring.boot.main.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Schema(
		description = "UserDto Model Information"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	
	private Long id;
	@Schema(
			description = 
	)
	@NotEmpty(message = "User first name must not be empty")
	private String firstName;
	@NotEmpty(message = "User last name must not be empty")
	private String lastName;
	@NotEmpty(message = "User email must not be empty")
	@Email(message = "Email address should be valid")
	private String email;
	
	

}
