package es.codeurjc.books.infrastructure;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import es.codeurjc.books.domain.BookRepository;
import es.codeurjc.books.domain.FullBookDto;
import es.codeurjc.books.infrastructure.model.BookEntity;
import es.codeurjc.books.infrastructure.repository.BookJpaRepository;

@Component
public class BookRepositoryAdapter implements BookRepository {

	private BookJpaRepository bookJpaRepository;

	public BookRepositoryAdapter(BookJpaRepository bookJpaRepository) {
		this.bookJpaRepository = bookJpaRepository;
	}
	
	@Override
	public FullBookDto save(FullBookDto book) {
		
		BookEntity bookEntity = new BookEntity(
				book.getId(),
				book.getTitle(),
				book.getSummary(),
				book.getAuthor(),
				book.getEditorial(),
				book.getPublishYear());
		
		BookEntity savedBookEntity = bookJpaRepository.save(bookEntity);
		
		return toFullBookDto(savedBookEntity);
	}

	@Override
	public List<FullBookDto> findAllBooks() {
		
		List<BookEntity> books = bookJpaRepository.findAll();
		
		return books
				.stream()
				.map(BookRepositoryAdapter::toFullBookDto)
				.collect(Collectors.toList());
		
	}
	
	@Override
	public Optional<FullBookDto> findBookById(Long id) {
		
		Optional<BookEntity> maybeABook = bookJpaRepository.findById(id);
		
		return maybeABook.map(BookRepositoryAdapter::toFullBookDto);
		
	}

	private static FullBookDto toFullBookDto(BookEntity book) {
		
		return new FullBookDto(
				book.getId(),
				book.getTitle(),
				book.getSummary(),
				book.getAuthor(),
				book.getEditorial(),
				book.getPublishYear());
	}

}
