package es.codeurjc.books.controller;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import es.codeurjc.books.infrastructure.model.BookEntity;
import es.codeurjc.books.infrastructure.model.CommentEntity;
import es.codeurjc.books.service.BookService;
import es.codeurjc.books.service.CommentService;
import es.codeurjc.books.service.UserService;

@RestController
public class CommentController {

	@Autowired
	private CommentService comments;

	@Autowired
	private UserService users;

	@Autowired
	private BookService books;

	@PostMapping("books/{id}/comments/")
	public ResponseEntity<?> createComment(@RequestBody CommentRequestDto comment,
			@PathVariable Long id) {

		String nick = null;
		if (comment.getUserNick() != null) {
			nick = comment.getUserNick();
		}

		if (nick == null) {
			return ResponseEntity.badRequest().body("User nick not provided");
		}

		comment.setUser(users.findByNick(nick).orElseThrow());

		books.saveComment(comment, id);

		URI location = fromCurrentRequest().path("/{id}")
				.build(comment.getId());

		return ResponseEntity.created(location).body(comment);
	}

	@JsonView(CommentWithBookId.class)
	@DeleteMapping("books/{bookId}/comments/{id}")
	public CommentEntity deleteComment(@PathVariable Long bookId,
			@PathVariable Long id) {

		CommentEntity comment = comments.findById(id).orElseThrow();

		CommentEntity commentToReturn = new CommentEntity(comment);

		books.deleteCommentById(bookId, id);

		return commentToReturn;
	}

	@GetMapping("/users/{id}/comments")
	@JsonView(CommentWithBookId.class)
	public List<CommentEntity> getAllCommentsFromUser(@PathVariable long id) {

		if (!users.existsById(id)) {
			throw new NoSuchElementException();
		}

		return comments.findAllCommentsByUserId(id);
	}

}
