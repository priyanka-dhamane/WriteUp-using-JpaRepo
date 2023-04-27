package com.writeup.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.writeup.dao.CategoryDao;
import com.writeup.dao.PostDao;
import com.writeup.dao.UserDao;
import com.writeup.entity.Category;
import com.writeup.entity.Post;
import com.writeup.entity.User;
import com.writeup.exceptions.ResorceNotFoundException;
import com.writeup.model.PostDto;
import com.writeup.model.PostResponse;
import com.writeup.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostDao postDao;

	@Autowired
	private CategoryDao categoryDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

		User user = this.userDao.findById(userId)
				.orElseThrow(() -> new ResorceNotFoundException("user", "user is", userId));
		Category category = this.categoryDao.findById(categoryId)
				.orElseThrow(() -> new ResorceNotFoundException("category", "category id", categoryId));
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		Post newPost = this.postDao.save(post);
		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post = this.postDao.findById(postId)
				.orElseThrow(() -> new ResorceNotFoundException("post", "post id", postId));
		post.setPostTitle(postDto.getPostTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		Post updatedPost = this.postDao.save(post);
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post = this.postDao.findById(postId)
				.orElseThrow(() -> new ResorceNotFoundException("post", "post id", postId));
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
		// for pagination and sorting using if else
		//Sort sort=null;
		//if(sortDir.equalsIgnoreCase("asc")) {
		//	sort=Sort.by(sortBy).ascending();
		//}else {
		//	Sort.by(sortBy).descending();
		//}

		// for pagination and sorting using ternary opraters
		Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		Page<Post> pagePosts = this.postDao.findAll(pageable);
		List<Post> allPosts = pagePosts.getContent();
		List<PostDto> postDtos = allPosts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePosts.getNumber());
		postResponse.setPageSize(pagePosts.getSize());
		postResponse.setTotalRecords(pagePosts.getTotalElements());
		postResponse.setLastPage(pagePosts.isLast());
		postResponse.setTotalPages(pagePosts.getTotalPages());

		return postResponse;
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = this.postDao.findById(postId)
				.orElseThrow(() -> new ResorceNotFoundException("post", "post id", postId));
		this.postDao.delete(post);
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		Category category = this.categoryDao.findById(categoryId)
				.orElseThrow(() -> new ResorceNotFoundException("category", "category id", categoryId));
		List<Post> posts = this.postDao.findByCategory(category);
		List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		User user = this.userDao.findById(userId)
				.orElseThrow(() -> new ResorceNotFoundException("user", "user id", userId));
		List<Post> posts = this.postDao.findByUser(user);
		List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return postDtos;

	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Post> posts = this.postDao.searchByPostTitle("%" + keyword + "%");
		List<PostDto> postDtos = posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

}
