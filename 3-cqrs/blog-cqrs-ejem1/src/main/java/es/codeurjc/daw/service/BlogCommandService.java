package es.codeurjc.daw.service;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.daw.controller.CommentDto;
import es.codeurjc.daw.controller.FullPostDto;
import es.codeurjc.daw.model.Comment;
import es.codeurjc.daw.model.Post;
import es.codeurjc.daw.repository.CommentRepository;
import es.codeurjc.daw.repository.PostRepository;

@Service
public class BlogCommandService {

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public void addPost(FullPostDto post) {
		postRepository.save(convertFullPostDtoToEntity(post));
	}

	public void addComment(long postId, CommentDto comment) {
		Post post = postRepository.findById(postId).orElseThrow(EntityNotFoundException::new);
		
		Comment savedComment = commentRepository.save(convertCommentDtoToEntity(comment));
		
		post.getComments().add(savedComment);
		
		postRepository.save(post);
	}

	public void deleteComment(long postId, long commentId) {
		
		Post post = postRepository.findById(postId).orElseThrow(EntityNotFoundException::new);
		
		post.deleteComment(commentRepository.findById(commentId).orElseThrow(EntityNotFoundException::new));
		postRepository.save(post);
		commentRepository.deleteById(commentId);
	}

	private Post convertFullPostDtoToEntity(FullPostDto post) {
		return modelMapper.map(post, Post.class);
	}

	private Comment convertCommentDtoToEntity(CommentDto comment) {
		return modelMapper.map(comment, Comment.class);
	}


}
