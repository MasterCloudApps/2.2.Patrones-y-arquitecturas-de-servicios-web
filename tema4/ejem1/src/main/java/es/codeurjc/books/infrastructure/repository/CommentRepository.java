package es.codeurjc.books.infrastructure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.codeurjc.books.infrastructure.model.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

	@Query("SELECT DISTINCT p FROM Comment p JOIN p.user u WHERE u.id=?1")
	List<CommentEntity> findAllCommentsByUserId(Long userId);

}