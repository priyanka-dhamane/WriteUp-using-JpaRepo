package com.writeup.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.writeup.model.ApiResponse;
import com.writeup.model.CommentDto;
import com.writeup.service.CommentService;

@RestController
@RequestMapping("/comments")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	// create
		@PostMapping("/post/{postId}/comments")
		public ResponseEntity<CommentDto> createPost(@Valid @RequestBody CommentDto commentDto, @PathVariable Integer commentId) {
			CommentDto createComment = this.commentService.createComment(commentDto, commentId);
			return new ResponseEntity<CommentDto>(createComment, HttpStatus.CREATED);
		}
		
		@DeleteMapping("comments/{commentId}")
		public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId) {
			this.commentService.deleteComment(commentId);
			return new ResponseEntity<ApiResponse>(new ApiResponse("comment deleted successfully", true), HttpStatus.OK);
		}


}
