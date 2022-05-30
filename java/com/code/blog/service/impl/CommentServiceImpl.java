package com.code.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.code.blog.entity.Comment;
import com.code.blog.entity.Post;
import com.code.blog.payload.CommentDto;
import com.code.blog.repository.CommentRepository;
import com.code.blog.repository.PostRepository;
import com.code.blog.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	private CommentRepository commentRepository;
	private PostRepository postRepository;

	public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {

		this.commentRepository = commentRepository;
		this.postRepository = postRepository;
	}

	@Override
	public CommentDto createComment(CommentDto commentDto, long postId) {
		Comment comment = mapToEntity(commentDto);

		Post post = postRepository.findById(postId).get();
		comment.setPost(post);
		Comment newComment = commentRepository.save(comment);
		return mapToCommentDto(newComment);
	}

	private CommentDto mapToCommentDto(Comment comment) {

		CommentDto commentDto = new CommentDto();
		commentDto.setId(comment.getId());
		commentDto.setName(comment.getName());
		commentDto.setEmail(comment.getEmail());
		commentDto.setBody(comment.getBody());

		return commentDto;
	}

	private Comment mapToEntity(CommentDto commentDto) {

		Comment comment = new Comment();
		comment.setName(commentDto.getName());
		comment.setEmail(commentDto.getEmail());
		comment.setBody(commentDto.getBody());

		return comment;
	}

	@Override
	public List<CommentDto> getCommentByPostId(long postId) {

		List<Comment> listComment = commentRepository.getByPostId(postId);
		return listComment.stream().map(comment -> mapToCommentDto(comment)).collect(Collectors.toList());
	}

}
