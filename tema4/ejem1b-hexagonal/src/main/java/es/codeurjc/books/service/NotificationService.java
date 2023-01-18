package es.codeurjc.books.service;

import org.springframework.stereotype.Service;

import es.codeurjc.books.domain.port.BookDto;
import es.codeurjc.books.domain.port.NotificationUseCase;

@Service
public class NotificationService {

    NotificationUseCase useCase;

    public NotificationService(NotificationUseCase useCase) {
        this.useCase = useCase;
    }

    public void receive(BookDto book) {
        useCase.receive(book);
    }

}
