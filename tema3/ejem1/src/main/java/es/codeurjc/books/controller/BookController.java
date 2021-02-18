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

import es.codeurjc.books.domain.FullBookDto;
import es.codeurjc.books.infrastructure.model.BookEntity;
import es.codeurjc.books.infrastructure.model.CommentEntity;
import es.codeurjc.books.infrastructure.model.UserEntity;
import es.codeurjc.books.service.BookService;

@RestController
public class BookController {

	@Autowired
	private BookService books;

	@GetMapping("/books/")
	public Collection<BookResponseDto> getBooks() {
		return books.findAll();
	}

	@GetMapping("/books/{id}")
	public BookResponseDto getBook(@PathVariable long id) {

		return books.findById(id).orElseThrow();
	}

	@PostMapping("/books/")
	public ResponseEntity<BookResponseDto> createBook(@RequestBody BookRequestDto book) {

		FullBookDto fullBook = books.save(book);
		
		// Transform fullBook into BookResponseDto
		BookResponseDto responseBookDto = new BookResponseDto(
				fullBook.getId(), 
				fullBook.getTitle(), 
				fullBook.getSummary(), 
				fullBook.getAuthor(), 
				fullBook.getEditorial(), 
				fullBook.getPublishYear()); 

		URI location = fromCurrentRequest().path("/{id}")
				.buildAndExpand(fullBook.getId()).toUri();

		return ResponseEntity.created(location).body(responseBookDto);
	}

}