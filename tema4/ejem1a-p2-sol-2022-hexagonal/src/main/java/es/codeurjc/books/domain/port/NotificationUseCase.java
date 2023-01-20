package es.codeurjc.books.domain.port;

public interface NotificationUseCase {
    
    public void receive(BookDto message);
}
