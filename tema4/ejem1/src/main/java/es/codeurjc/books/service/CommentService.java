package es.codeurjc.books.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.books.infrastructure.model.CommentEntity;
import es.codeurjc.books.infrastructure.repository.CommentRepository;

@Service
public class CommentService {

	@Autowired
	private CommentRepository comments;

	public Optional<CommentEntity> findById(long id) {
		return comments.findById(id);
	}

	public void save(CommentEntity comment) {
		this.comments.save(comment);
	}

	public void delete(CommentEntity comment) {
		this.comments.delete(comment);
	}

	public List<CommentEntity> findAllCommentsByUserId(Long userId) {
		return comments.findAllCommentsByUserId(userId);
	}
	
}
