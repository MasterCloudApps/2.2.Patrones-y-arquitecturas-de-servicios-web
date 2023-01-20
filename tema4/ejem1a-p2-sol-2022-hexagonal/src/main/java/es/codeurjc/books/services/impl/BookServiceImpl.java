package es.codeurjc.books.services.impl;

import java.util.Collection;
import java.util.stream.Collectors;

import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import es.codeurjc.books.domain.port.BookDto;
import es.codeurjc.books.domain.port.BookUseCase;
import es.codeurjc.books.dtos.requests.BookRequestDto;
import es.codeurjc.books.dtos.responses.BookDetailsResponseDto;
import es.codeurjc.books.dtos.responses.BookResponseDto;
import es.codeurjc.books.exceptions.BookNotFoundException;
import es.codeurjc.books.infrastructure.models.BookEntity;
import es.codeurjc.books.infrastructure.repositories.BookJpaRepository;
import es.codeurjc.books.services.BookService;

@Service
public class BookServiceImpl implements BookService {

    private Mapper mapper;
    private BookUseCase bookUseCase;

    public BookServiceImpl(Mapper mapper, BookUseCase bookUseCase) {
        this.mapper = mapper;
        this.bookUseCase = bookUseCase;
    }

    public Collection<BookResponseDto> findAll() {
    	
        return this.bookUseCase.findAllBooks().stream()
                .map(book -> this.mapper.map(book, BookResponseDto.class))
                .collect(Collectors.toList());
        
    }

    public BookDetailsResponseDto save(BookRequestDto bookRequestDto) {
    	
    	return this.mapper.map(
    			bookUseCase.createBook(mapper.map(bookRequestDto, BookDto.class)),
    			BookDetailsResponseDto.class);
    	
    }

    public BookDetailsResponseDto findById(long bookId) {
    	
    	return mapper.map(this.bookUseCase.findBookById(bookId).orElseThrow(), BookDetailsResponseDto.class);
    	
    }

}
