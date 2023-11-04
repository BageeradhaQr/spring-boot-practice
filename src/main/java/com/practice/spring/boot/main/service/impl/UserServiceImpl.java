package com.practice.spring.boot.main.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.practice.spring.boot.main.dto.UserDto;
import com.practice.spring.boot.main.entity.User;
import com.practice.spring.boot.main.exception.EmailAlreadyExistsException;
import com.practice.spring.boot.main.exception.ResourceNotFoundException;
import com.practice.spring.boot.main.mapper.AutoUserMapper;
import com.practice.spring.boot.main.mapper.UserMapper;
import com.practice.spring.boot.main.repository.UserRepository;
import com.practice.spring.boot.main.service.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
	
	private static final String EMAIL_ALREADY_EXISTED = "Email Already Existed.";

	//@Autowired
	private UserRepository userRepository;
	
	private ModelMapper modelMapper ;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		// convert userdto into user
		//User user = UserMapper.mapToUser(userDto);
		//User user = modelMapper.map(userDto, User.class);
		Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
		if(optionalUser.isPresent()) {
			throw new EmailAlreadyExistsException(EMAIL_ALREADY_EXISTED);
		}
		User user = AutoUserMapper.MAPPER.mapToUser(userDto);
		
		User savedUser = userRepository.save(user);
		// convert User JPA entity to UserDTO
		//UserDto dto = UserMapper.mapToUserDto(savedUser);
		//UserDto dto = modelMapper.map(savedUser, UserDto.class);
		UserDto dto = AutoUserMapper.MAPPER.mapToUserDto(savedUser);
		return dto;
	}


	@Override
	public UserDto findByUserId(Long userId) {
		User optionalUser = userRepository.findById(userId).orElseThrow(
				() -> new ResourceNotFoundException("User", "id", userId)
				);
		//User user = optionalUser.get();
		//UserDto dto =  UserMapper.mapToUserDto(user);
		//UserDto dto = modelMapper.map(user, UserDto.class);
		UserDto dto = AutoUserMapper.MAPPER.mapToUserDto(optionalUser);
		return dto;
	}


	@Override
	public List<UserDto> fetchAllUser() {
		List<User> users = userRepository.findAll();
//		return users.stream().map(UserMapper:: mapToUserDto)
//				.collect(Collectors.toList());
//		return users.stream().map(user -> modelMapper.map(user, UserDto.class))
//				.collect(Collectors.toList());
		return users.stream().map(user -> AutoUserMapper.MAPPER.mapToUserDto(user))
				.collect(Collectors.toList());
	}


	@Override
	public UserDto updateUser(UserDto dto) {
		User existingUser = userRepository.findById(dto.getId()).get();
		existingUser.setFirstName(dto.getFirstName());
		existingUser.setLastName(dto.getLastName());
		existingUser.setEmail(dto.getEmail());
		User savedUser = userRepository.save(existingUser);
//		return UserMapper.mapToUserDto(savedUser);
//		return modelMapper.map(savedUser,UserDto.class);
		return AutoUserMapper.MAPPER.mapToUserDto(savedUser);
	}


	@Override
	public void deleteUser(Long userId) {
		userRepository.deleteById(userId);
	}

}
