package es.codeurjc.books;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import es.codeurjc.books.domain.BookRepository;
import es.codeurjc.books.domain.BookUseCase;
import es.codeurjc.books.domain.BookUseCaseImpl;

@Configuration
public class AppConfiguration {

	@Bean
	public BookUseCase bookUseCase(BookRepository repositoryAdapter) {
		return new BookUseCaseImpl(repositoryAdapter);
	}
	
}
