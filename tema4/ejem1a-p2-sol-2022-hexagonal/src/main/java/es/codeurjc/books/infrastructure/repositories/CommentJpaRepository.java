package es.codeurjc.books.infrastructure.repositories;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.books.infrastructure.models.CommentEntity;

public interface CommentJpaRepository extends JpaRepository<CommentEntity, Long> {

    Optional<CommentEntity> findByBookIdAndId(Long bookId, Long commentId);

    Collection<CommentEntity> findByUserId(long userId);
}
