package es.codeurjc.books;

import java.util.Arrays;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import es.codeurjc.books.domain.BookUseCaseImpl;
import es.codeurjc.books.domain.CommentUseCaseImpl;
import es.codeurjc.books.domain.UserUseCaseImpl;
import es.codeurjc.books.domain.port.BookRepository;
import es.codeurjc.books.domain.port.BookUseCase;
import es.codeurjc.books.domain.port.CommentRepository;
import es.codeurjc.books.domain.port.CommentUseCase;
import es.codeurjc.books.domain.port.UserRepository;
import es.codeurjc.books.domain.port.UserUseCase;
import es.codeurjc.books.infrastructure.BookRepositoryAdapter;
import es.codeurjc.books.infrastructure.CommentRepositoryAdapter;
import es.codeurjc.books.infrastructure.UserRepositoryAdapter;
import es.codeurjc.books.infrastructure.repositories.BookJpaRepository;
import es.codeurjc.books.infrastructure.repositories.CommentJpaRepository;
import es.codeurjc.books.infrastructure.repositories.UserJpaRepository;

@SpringBootApplication
public class Application {

    @Bean
    public Mapper mapper() {
        return new DozerBeanMapper(Arrays.asList("dozer_mapping.xml"));
    }

    @Bean
    public BookRepository bookRepository(BookJpaRepository bookJpaRepository, Mapper mapper) {
    	return new BookRepositoryAdapter(bookJpaRepository, mapper);
    }
    
    @Bean
    public BookUseCase bookUseCase(BookRepository bookRepository) {
    	return new BookUseCaseImpl(bookRepository);
    }
    
    @Bean
    public CommentRepository commentRepository(CommentJpaRepository commentJpaRepository, Mapper mapper) {
    	return new CommentRepositoryAdapter(commentJpaRepository, mapper);
    }
    
    @Bean
    public CommentUseCase commentUseCase(BookRepository bookRepository, CommentRepository commentRepository, Mapper mapper) {
    	return new CommentUseCaseImpl(bookRepository, commentRepository, mapper);
    }
    
    @Bean
    public UserRepository userRepository(UserJpaRepository bookJpaRepository, Mapper mapper) {
    	return new UserRepositoryAdapter(bookJpaRepository, mapper);
    }
    
    @Bean
    public UserUseCase userUseCase(UserRepository userRepository, CommentRepository commentRepository, Mapper mapper) {
    	return new UserUseCaseImpl(userRepository, commentRepository, mapper);
    }
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    

}
