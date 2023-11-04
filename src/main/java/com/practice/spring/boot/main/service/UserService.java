package com.practice.spring.boot.main.service;

import java.util.List;

import com.practice.spring.boot.main.dto.UserDto;

public interface UserService {
	
	public UserDto createUser(UserDto user);
	
	public UserDto findByUserId(Long userId);
	
	public List<UserDto> fetchAllUser();
	
	public UserDto updateUser(UserDto user);
	
	public void deleteUser(Long userId);

}
