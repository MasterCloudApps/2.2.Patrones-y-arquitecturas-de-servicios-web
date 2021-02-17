package es.codeurjc.books.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.books.model.Book;
import es.codeurjc.books.model.Comment;
import es.codeurjc.books.repository.BookRepository;
import es.codeurjc.books.repository.CommentRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository books;

	@Autowired
	private CommentRepository comments;

	public Collection<Book> findAll() {
		return books.findAll();
	}

	public Optional<Book> findById(Long id) {
		return books.findById(id);
	}

	public void save(Book book) {
		books.save(book);
	}

	public void deleteById(Long id) {
		books.deleteById(id);
	}

	public void saveComment(Comment comment, Long bookId) {

		Book book = books.findById(bookId).orElseThrow();

		book.addComment(comment);

		comments.save(comment);
		books.save(book);
	}

	public void deleteCommentById(Long bookId, Long id) {

		Comment comment = comments.findById(id).orElseThrow();
		Book book = books.findById(bookId).orElseThrow();

		book.removeComment(comment);

		books.save(book);
		comments.delete(comment);
	}
}
