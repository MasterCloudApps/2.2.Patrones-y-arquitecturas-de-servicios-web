package es.codeurjc.books.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.books.infrastructure.models.BookEntity;

public interface BookJpaRepository extends JpaRepository<BookEntity, Long> {

}
