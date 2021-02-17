package es.codeurjc.books.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.books.model.Comment;
import es.codeurjc.books.repository.CommentRepository;

@Service
public class CommentService {

	@Autowired
	private CommentRepository comments;

	public Optional<Comment> findById(long id) {
		return comments.findById(id);
	}

	public void save(Comment comment) {
		this.comments.save(comment);
	}

	public void delete(Comment comment) {
		this.comments.delete(comment);
	}

	public List<Comment> findAllCommentsByUserId(Long userId) {
		return comments.findAllCommentsByUserId(userId);
	}
	
}
