package com.writeup.service;

import java.util.List;

import com.writeup.model.PostDto;
import com.writeup.model.PostResponse;

public interface PostService {
	// create
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

	// update
	PostDto updatePost(PostDto postDto, Integer postId);

	// get single post
	PostDto getPostById(Integer postId);

	// get all posts
	PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);

	// delete
	void deletePost(Integer postId);

	// get all post by category
	List<PostDto> getPostsByCategory(Integer categoryId);

	// get all post by user
	List<PostDto> getPostsByUser(Integer userId);

	// search posts
	List<PostDto> searchPosts(String keyword);

}
