package es.codeurjc.books.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "User can't be deleted because has associated comments")
public class UserCanNotBeDeletedException extends RuntimeException {

	private static final long serialVersionUID = 1L;
}
