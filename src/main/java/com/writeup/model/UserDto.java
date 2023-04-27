package com.writeup.model;



import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private int id;
	@NotEmpty
	@Size(min=4,message="username should be minmum of 4 characters!!")
	private String name;
	@Email(message="your email isnot valid!!")
	private String email;
	@NotEmpty()
	@Size(min=3,max=8 , message="password must be greater than 3 char and less than 8 char!!")
	private String password;
	@NotNull
	private String about;

}
