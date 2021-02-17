package es.codeurjc.books.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.codeurjc.books.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	@Query("SELECT DISTINCT p FROM Comment p JOIN p.user u WHERE u.id=?1")
	List<Comment> findAllCommentsByUserId(Long userId);

}