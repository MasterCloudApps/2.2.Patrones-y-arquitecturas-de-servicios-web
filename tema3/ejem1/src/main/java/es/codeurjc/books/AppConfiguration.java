package es.codeurjc.books;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import es.codeurjc.books.domain.BookUseCaseImpl;
import es.codeurjc.books.domain.port.BookRepository;
import es.codeurjc.books.domain.port.BookUseCase;
import es.codeurjc.books.domain.port.NotificationUseCase;
import es.codeurjc.books.domain.port.NotificationUseCaseImpl;

@Configuration
public class AppConfiguration {

	@Bean
	public BookUseCase bookUseCase(BookRepository repositoryAdapter) {
		return new BookUseCaseImpl(repositoryAdapter);
	}

	@Bean
	public NotificationUseCase notificationUseCase() {
		return new NotificationUseCaseImpl();
	}
	
}
