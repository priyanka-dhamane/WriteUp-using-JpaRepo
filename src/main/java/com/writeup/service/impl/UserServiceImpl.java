package com.writeup.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.writeup.dao.UserDao;
import com.writeup.entity.User;
import com.writeup.exceptions.ResorceNotFoundException;
import com.writeup.model.UserDto;
import com.writeup.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDto createUser(UserDto UserDto) {

		User user = this.dtoToUser(UserDto);
		User saveUser = this.userDao.save(user);
		return this.UserToDto(saveUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user = this.userDao.findById(userId).orElseThrow(() -> new ResorceNotFoundException("User", "Id", userId));

		user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		User updatedUser = this.userDao.save(user);
		return this.UserToDto(updatedUser);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = this.userDao.findById(userId).orElseThrow(() -> new ResorceNotFoundException("User", "Id", userId));
		return this.UserToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = this.userDao.findAll();
		List<UserDto> userDtos = users.stream().map(user -> this.UserToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userDao.findById(userId).orElseThrow(() -> new ResorceNotFoundException("User", "Id", userId));
		this.userDao.delete(user);
	}

	private User dtoToUser(UserDto userDto) {

		User user = this.modelMapper.map(userDto, User.class);
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());

		return user;

	}

	private UserDto UserToDto(User user) {

		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		
		return userDto;

	}


}
