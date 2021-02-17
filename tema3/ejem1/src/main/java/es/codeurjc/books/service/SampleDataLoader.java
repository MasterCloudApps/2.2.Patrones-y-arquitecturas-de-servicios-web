package es.codeurjc.books.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.books.model.Book;
import es.codeurjc.books.model.Comment;
import es.codeurjc.books.model.User;
import es.codeurjc.books.repository.BookRepository;
import es.codeurjc.books.repository.UserRepository;

@Service
public class SampleDataLoader {

	@Autowired
	private BookRepository books;
	
	@Autowired
	private UserRepository users;

	@PostConstruct
	public void init() {

		User user1 = new User("pepe", "pepe@gmail.com");
		User user2 = new User("juan", "juan@hotmail.com");
		User user3 = new User("rafa", "rafa85@terra.es");

		users.save(user1);
		users.save(user2);
		users.save(user3);
		
		Book book1 = new Book("Don Quijote", "En un lugar de la Mancha",
				"Cervantes", "Desconocido", 1605);

		book1.addComment(new Comment(user1, 5, "un clásico"));
		book1.addComment(new Comment(user2, 0, "no me ha gustado"));
		
		Book book2 = new Book("El principito", "Un piloto se pierde en el Sáhara",
				"Antoine de Saint-Exupéry", "Gallimard", 1943);
		
		book2.addComment(new Comment(user2, 0, "este tampoco"));
		
		Book book3 = new Book("Lazarillo de Tormes", "La vida de Lazarillo de Tormes y de sus fortunas y adversidades",
				"Desconocido", "Acceso público", 1554);
		
		books.save(book1);		
		books.save(book2);
		books.save(book3);
	}

}
