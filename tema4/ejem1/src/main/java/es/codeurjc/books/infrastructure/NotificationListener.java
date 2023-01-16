package es.codeurjc.books.infrastructure;

import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import es.codeurjc.books.domain.port.BookDto;
import es.codeurjc.books.service.NotificationService;

@Component
public class NotificationListener {
    
    private NotificationService notificationService;

    public NotificationListener(NotificationService service) {
        this.notificationService = service;
    }

    @Bean
    public Consumer<BookDto> consumer() {
        return (book) -> notificationService.receive(book);
    }

}
