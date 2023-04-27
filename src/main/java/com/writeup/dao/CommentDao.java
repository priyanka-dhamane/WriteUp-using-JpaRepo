package com.writeup.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.writeup.entity.Comment;

public interface CommentDao extends JpaRepository<Comment, Integer> {

}
