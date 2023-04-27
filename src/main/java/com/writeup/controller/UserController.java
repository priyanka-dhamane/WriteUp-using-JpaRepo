package com.writeup.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.writeup.model.ApiResponse;
import com.writeup.model.UserDto;
import com.writeup.service.UserService;
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	//create user
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		UserDto createUserDto=userService.createUser(userDto);
		return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
	}
	
	//Update user
		@PutMapping("/{userId}")
		public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable Integer userId){
			UserDto updatedUser=this.userService.updateUser(userDto,userId);
			return  ResponseEntity.ok(updatedUser);
		}
		
		//delete user
		@DeleteMapping("/{userId}")
		public ResponseEntity<?> deleteUser(@PathVariable Integer userId){
			this.userService.deleteUser(userId);
			return new ResponseEntity<ApiResponse>(new ApiResponse("user deleted successfully", true),HttpStatus.OK);
		}
		
		//get all users
		@GetMapping
		public ResponseEntity<List<UserDto>> getAllUser(){
			return ResponseEntity.ok(this.userService.getAllUsers());
		}
		
		//get user by id
		@GetMapping("/{userId}")
		public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId){
			return ResponseEntity.ok(this.userService.getUserById(userId));
		}
		
		

}
