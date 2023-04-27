package com.writeup.service;

import com.writeup.model.CommentDto;

public interface CommentService {
CommentDto createComment(CommentDto commenetDto,Integer postId);
void deleteComment(Integer commentId);
}
