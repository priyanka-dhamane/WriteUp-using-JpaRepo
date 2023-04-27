package com.writeup.service;

import java.util.List;

import com.writeup.model.UserDto;

public interface UserService {
	UserDto createUser(UserDto userDto);
	UserDto updateUser(UserDto userDto,Integer userId);
	UserDto getUserById(Integer userId );
	List<UserDto> getAllUsers();
	void deleteUser(Integer UserId);
	

}
