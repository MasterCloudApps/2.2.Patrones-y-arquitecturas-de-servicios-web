package es.codeurjc.booksmanagementspring.controllerAdvice;

import es.codeurjc.booksmanagementspring.exceptions.UserHasReviewsException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public void handleNoTFound() {
    }

    @ExceptionHandler(UserHasReviewsException.class)
    public ResponseEntity handleUserHasReviews(UserHasReviewsException exception) {
        return new ResponseEntity(exception.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
