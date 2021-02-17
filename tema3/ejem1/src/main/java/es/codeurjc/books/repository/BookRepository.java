package es.codeurjc.books.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.books.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}