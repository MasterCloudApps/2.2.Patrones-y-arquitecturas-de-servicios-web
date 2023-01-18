package es.codeurjc.books.repositories;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.books.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findByBookIdAndId(Long bookId, Long commentId);

    Collection<Comment> findByUserId(long userId);
}
