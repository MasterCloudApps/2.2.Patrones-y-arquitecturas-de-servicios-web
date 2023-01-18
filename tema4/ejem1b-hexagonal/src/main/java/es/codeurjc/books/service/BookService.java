package es.codeurjc.books.service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.books.controller.BookRequestDto;
import es.codeurjc.books.controller.BookResponseDto;
import es.codeurjc.books.domain.port.BookDto;
import es.codeurjc.books.domain.port.BookUseCase;
import es.codeurjc.books.domain.port.FullBookDto;
import es.codeurjc.books.infrastructure.model.BookEntity;
import es.codeurjc.books.infrastructure.model.CommentEntity;
import es.codeurjc.books.infrastructure.repository.BookJpaRepository;
import es.codeurjc.books.infrastructure.repository.CommentRepository;

@Service
public class BookService {

	private BookUseCase bookUseCase;
	private CommentUseCase commentUseCase;
	ModelMapper mapper = new ModelMapper();

	public BookService(BookUseCase bookUseCase, CommentUseCase commentUseCase) {
		this.bookUseCase = bookUseCase;
		this.commentUseCase = commentUseCase;
	}
	
	public Collection<BookResponseDto> findAll() {
		return bookUseCase
				.findAllBooks()
				.stream()
				.map(BookResponseDto::fromFullBookDto)
				.collect(Collectors.toList());
	}

	public Optional<BookResponseDto> findById(Long id) {
		return bookUseCase.findBookById(id).map(BookResponseDto::fromFullBookDto);
	}

	public FullBookDto save(BookRequestDto book) {
		
		BookDto bookDto = new BookDto(
				book.getTitle(),
				book.getSummary(),
				book.getAuthor(),
				book.getEditorial(),
				book.getPublishYear()
				);
		
		return bookUseCase.createBook(bookDto);
	}

	public void deleteById(Long id) {
		books.deleteById(id);
	}

	public void saveComment(CommentEntity comment, Long bookId) {

		CommentDto commentDto = mapper.map(comment, CommentDto.class);

		commentUseCase.save(commentDto, bookId);

	}

	public void deleteCommentById(Long bookId, Long id) {

		commentUseCase.deleteCommentById(bookId, id);

	}
}
