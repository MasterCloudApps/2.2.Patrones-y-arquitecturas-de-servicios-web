package es.codeurjc.books.domain;

import java.util.Collection;
import java.util.Optional;

import es.codeurjc.books.domain.port.BookDto;
import es.codeurjc.books.domain.port.BookRepository;
import es.codeurjc.books.domain.port.BookUseCase;
import es.codeurjc.books.domain.port.FullBookDto;

public class BookUseCaseImpl implements BookUseCase {

	private BookRepository bookRepository;

	public BookUseCaseImpl(BookRepository repository) {
		this.bookRepository = repository;
	}
	
	@Override
	public FullBookDto createBook(BookDto bookDto) {
		FullBookDto book = new FullBookDto(
				bookDto.getTitle(), 
				bookDto.getSummary(),
				bookDto.getAuthor(), 
				bookDto.getPublisher(),
				bookDto.getPublishYear());
		
		FullBookDto fullBook = bookRepository.save(book);
		return fullBook;
	}

	@Override
	public Collection<FullBookDto> findAllBooks() {
		return bookRepository.findAllBooks();
	}

	@Override
	public Optional<FullBookDto> findBookById(Long id) {
		return bookRepository.findBookById(id);
	}

}
