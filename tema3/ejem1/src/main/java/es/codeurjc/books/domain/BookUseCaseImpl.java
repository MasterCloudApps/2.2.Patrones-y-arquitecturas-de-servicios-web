package es.codeurjc.books.domain;

import java.util.Collection;
import java.util.Optional;

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
				bookDto.getEditorial(),
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
