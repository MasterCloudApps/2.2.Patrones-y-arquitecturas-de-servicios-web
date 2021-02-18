package es.codeurjc.books.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.books.infrastructure.model.BookEntity;
import es.codeurjc.books.infrastructure.model.CommentEntity;
import es.codeurjc.books.infrastructure.model.UserEntity;
import es.codeurjc.books.infrastructure.repository.BookJpaRepository;
import es.codeurjc.books.infrastructure.repository.UserRepository;

@Service
public class SampleDataLoader {

	@Autowired
	private BookJpaRepository books;
	
	@Autowired
	private UserRepository users;

	@PostConstruct
	public void init() {

		UserEntity user1 = new UserEntity("pepe", "pepe@gmail.com");
		UserEntity user2 = new UserEntity("juan", "juan@hotmail.com");
		UserEntity user3 = new UserEntity("rafa", "rafa85@terra.es");

		users.save(user1);
		users.save(user2);
		users.save(user3);
		
		BookEntity book1 = new BookEntity("Don Quijote", "En un lugar de la Mancha",
				"Cervantes", "Desconocido", 1605);

		book1.addComment(new CommentEntity(user1, 5, "un clásico"));
		book1.addComment(new CommentEntity(user2, 0, "no me ha gustado"));
		
		BookEntity book2 = new BookEntity("El principito", "Un piloto se pierde en el Sáhara",
				"Antoine de Saint-Exupéry", "Gallimard", 1943);
		
		book2.addComment(new CommentEntity(user2, 0, "este tampoco"));
		
		BookEntity book3 = new BookEntity("Lazarillo de Tormes", "La vida de Lazarillo de Tormes y de sus fortunas y adversidades",
				"Desconocido", "Acceso público", 1554);
		
		books.save(book1);		
		books.save(book2);
		books.save(book3);
	}

}
