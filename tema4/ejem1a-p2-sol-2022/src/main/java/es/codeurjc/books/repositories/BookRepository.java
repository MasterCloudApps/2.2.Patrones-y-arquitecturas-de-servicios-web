package es.codeurjc.books.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.books.models.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
