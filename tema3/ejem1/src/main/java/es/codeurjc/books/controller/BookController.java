package es.codeurjc.books.controller;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.net.URI;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import es.codeurjc.books.model.Book;
import es.codeurjc.books.model.Comment;
import es.codeurjc.books.model.User;
import es.codeurjc.books.service.BookService;

@RestController
public class BookController {

	@Autowired
	private BookService books;

	@JsonView(Book.Basic.class)
	@GetMapping("/books/")
	public Collection<Book> getBooks() {
		return books.findAll();
	}

	interface BookWithCommentsAndUser extends Book.Basic, Book.WithComment,
			Comment.Basic, Comment.WithUser, User.Basic {
	}

	@GetMapping("/books/{id}")
	@JsonView(BookWithCommentsAndUser.class)
	public Book getBook(@PathVariable long id) {

		return books.findById(id).orElseThrow();
	}

	@PostMapping("/books/")
	@JsonView(Book.Basic.class)
	public ResponseEntity<Book> createBook(@RequestBody Book book) {

		books.save(book);

		URI location = fromCurrentRequest().path("/{id}")
				.buildAndExpand(book.getId()).toUri();

		return ResponseEntity.created(location).body(book);
	}

}