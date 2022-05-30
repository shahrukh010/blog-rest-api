package com.code.blog.service;

import java.util.List;

import com.code.blog.payload.CommentDto;

public interface CommentService {

	public CommentDto createComment(CommentDto commentDto, long postId);

	public List<CommentDto> getCommentByPostId(long postId);

}
