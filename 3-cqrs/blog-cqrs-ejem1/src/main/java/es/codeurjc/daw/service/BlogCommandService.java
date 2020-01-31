package es.codeurjc.daw.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	public void addPost(Post post) {
		postRepository.save(post);
	}

	public void addComment(long postId, Comment comment) {
		Post post = postRepository.findById(postId).orElseThrow(EntityNotFoundException::new);
		
		Comment savedComment = commentRepository.save(comment);
		
		post.getComments().add(savedComment);
		
		postRepository.save(post);
	}

	public void deleteComment(long postId, long commentId) {
		
		Post post = postRepository.findById(postId).orElseThrow(EntityNotFoundException::new);
		
		post.deleteComment(commentRepository.findById(commentId).orElseThrow(EntityNotFoundException::new));
		commentRepository.deleteById(commentId);
	}

}
