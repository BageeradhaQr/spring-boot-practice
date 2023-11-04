package com.practice.spring.boot.main.mapper;

import com.practice.spring.boot.main.dto.UserDto;
import com.practice.spring.boot.main.entity.User;

public class UserMapper {
	
	public static UserDto mapToUserDto(User user) {
		UserDto userDto = new UserDto(user.getId(), user.getFirstName(),
				user.getLastName(), user.getEmail());
		return userDto;
	}
	
	public static User mapToUser(UserDto dto) {
		User user = new User(dto.getId(), dto.getFirstName(),
				dto.getLastName(), dto.getEmail());
		return user;
	}

}
