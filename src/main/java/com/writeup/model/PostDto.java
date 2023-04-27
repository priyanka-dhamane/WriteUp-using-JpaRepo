package com.writeup.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import com.writeup.entity.Comment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class PostDto {
	private Integer postId;
	@NotNull
	private String postTitle;
	@NotNull
	private String content;
	
	private String imageName;
	
	private Date addedDate;
	
	private CategoryDto category;
	private UserDto user;
	private Set<CommentDto> comments=new HashSet<>();
	 
}
