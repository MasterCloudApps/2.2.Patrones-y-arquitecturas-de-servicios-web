package es.codeurjc.books.infrastructure;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.dozer.Mapper;
import org.springframework.stereotype.Component;

import es.codeurjc.books.domain.port.BookRepository;
import es.codeurjc.books.domain.port.FullBookDto;
import es.codeurjc.books.infrastructure.models.BookEntity;
import es.codeurjc.books.infrastructure.repositories.BookJpaRepository;

@Component
public class BookRepositoryAdapter implements BookRepository {

	private BookJpaRepository bookJpaRepository;
	private Mapper mapper;

	public BookRepositoryAdapter(BookJpaRepository bookJpaRepository, Mapper mapper) {
		this.bookJpaRepository = bookJpaRepository;
		this.mapper = mapper;
	}
	
	@Override
	public FullBookDto save(FullBookDto book) {
		
		BookEntity bookEntity = mapper.map(book, BookEntity.class);
		
		BookEntity savedBookEntity = bookJpaRepository.save(bookEntity);
		
		return mapper.map(savedBookEntity, FullBookDto.class);
	}

	@Override
	public List<FullBookDto> findAllBooks() {
		
		List<BookEntity> books = bookJpaRepository.findAll();
		
		return books
				.stream()
				.map(b -> mapper.map(b, FullBookDto.class))
				.collect(Collectors.toList());
		
	}
	
	@Override
	public Optional<FullBookDto> findBookById(Long id) {
		
		Optional<BookEntity> maybeABook = bookJpaRepository.findById(id);
		
		System.out.println(maybeABook.get().getId());
		
		return maybeABook.map(b -> mapper.map(b, FullBookDto.class));
		
	}

}
