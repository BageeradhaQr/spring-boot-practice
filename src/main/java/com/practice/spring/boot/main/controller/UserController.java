package com.practice.spring.boot.main.controller;

import java.time.LocalDateTime;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.practice.spring.boot.main.dto.UserDto;
import com.practice.spring.boot.main.exception.ErrorDetails;
import com.practice.spring.boot.main.exception.ResourceNotFoundException;
import com.practice.spring.boot.main.service.UserService;

import lombok.AllArgsConstructor;
@Tag(
		name = "Manage User",
		description = "Create,fetch all ,find by ,delete by id"
)
@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
	
	//@Autowired
	private UserService userService;
	@Operation(
			summary = "This service called by frontend while registering a user and returns the response with required user data.",
			description = "create user REST api to save user into db"
	)
	@ApiResponse(
			responseCode = "201",
			description = "HTTP status 201 CREATED"
	)
	@PostMapping("/createUser")
	public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto user){
		UserDto savedUser = userService.createUser(user);
		return new ResponseEntity<UserDto>(savedUser, HttpStatus.CREATED);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<UserDto> findByUserId(@PathVariable("id") Long userId){
		UserDto dto = userService.findByUserId(userId);
		return new ResponseEntity<UserDto>(dto,HttpStatus.FOUND);
	}
	
	@GetMapping("/fetchAllUsers")
	public ResponseEntity<List<UserDto>> fetchAllUsers(){
		List<UserDto> dtos =  userService.fetchAllUser();
		return new ResponseEntity<>(dtos, HttpStatus.OK);
		//return new ResponseEntity<List<User>>(HttpStatus.OK).ok(userService.fetchAllUser());
	}
	
	@PutMapping("updateUser/{id}")
	public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId,@RequestBody @Valid UserDto user){
		user.setId(userId);
		UserDto updatedUser = userService.updateUser(user);
		return new ResponseEntity<>(updatedUser,HttpStatus.OK);
	}
	
	@DeleteMapping("deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
    	userService.deleteUser(userId);
    	return new ResponseEntity<String>("Deleted Record successfully::",HttpStatus.OK);
    }
	

}
