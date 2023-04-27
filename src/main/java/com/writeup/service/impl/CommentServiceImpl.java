package com.writeup.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.writeup.dao.CommentDao;
import com.writeup.dao.PostDao;
import com.writeup.entity.Comment;
import com.writeup.entity.Post;
import com.writeup.exceptions.ResorceNotFoundException;
import com.writeup.model.CommentDto;
import com.writeup.service.CommentService;
@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private PostDao postDao;

	@Autowired
	private CommentDao commentDao;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDto createComment(CommentDto commenetDto, Integer postId) {
		Post post = this.postDao.findById(postId)
				.orElseThrow(() -> new ResorceNotFoundException("post", "post id", postId));
		Comment comment = this.modelMapper.map(commenetDto, Comment.class);
		comment.setPost(post);
		Comment savedComment = this.commentDao.save(comment);
		return this.modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment comment = this.commentDao.findById(commentId)
				.orElseThrow(() -> new ResorceNotFoundException("comment", "comment id", commentId));
		this.commentDao.delete(comment);
	}

}
