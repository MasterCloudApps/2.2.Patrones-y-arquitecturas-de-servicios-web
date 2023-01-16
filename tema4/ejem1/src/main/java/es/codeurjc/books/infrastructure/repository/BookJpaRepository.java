package es.codeurjc.books.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.books.infrastructure.model.BookEntity;

public interface BookJpaRepository extends JpaRepository<BookEntity, Long> {

}