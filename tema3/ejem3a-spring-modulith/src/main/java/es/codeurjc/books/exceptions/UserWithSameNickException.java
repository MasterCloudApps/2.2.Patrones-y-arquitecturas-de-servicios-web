package es.codeurjc.books.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "User with same nick exists")
public class UserWithSameNickException extends RuntimeException {

	private static final long serialVersionUID = -5763510899245664744L;
}
