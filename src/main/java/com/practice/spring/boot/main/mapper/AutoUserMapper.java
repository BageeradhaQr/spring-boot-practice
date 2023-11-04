package com.practice.spring.boot.main.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.practice.spring.boot.main.dto.UserDto;
import com.practice.spring.boot.main.entity.User;

@Mapper
public interface AutoUserMapper {
	
	// how to create a AutoUserMapper implementation class automatically by using mapstruct
	
	AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);
	
	UserDto mapToUserDto(User user);
	
	User mapToUser(UserDto dto);


}
