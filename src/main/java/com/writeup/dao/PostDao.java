package com.writeup.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.writeup.entity.Category;
import com.writeup.entity.Post;
import com.writeup.entity.User;

public interface PostDao extends JpaRepository<Post, Integer> {
	List<Post> findByUser(User user);

	List<Post> findByCategory(Category category);

	@Query("select p from Post p where p.postTitle like :key")
	List<Post> searchByPostTitle(@Param("key") String postTitle);
}
