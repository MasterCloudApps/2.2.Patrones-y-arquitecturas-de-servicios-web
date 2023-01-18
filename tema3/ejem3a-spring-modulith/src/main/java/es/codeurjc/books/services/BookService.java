package es.codeurjc.books.services;

import java.util.Collection;

import es.codeurjc.books.dtos.requests.BookRequestDto;
import es.codeurjc.books.dtos.responses.BookDetailsResponseDto;
import es.codeurjc.books.dtos.responses.BookResponseDto;

public interface BookService {

    Collection<BookResponseDto> findAll();

    BookDetailsResponseDto save(BookRequestDto bookRequestDto);

    BookDetailsResponseDto findById(long bookId);

}
