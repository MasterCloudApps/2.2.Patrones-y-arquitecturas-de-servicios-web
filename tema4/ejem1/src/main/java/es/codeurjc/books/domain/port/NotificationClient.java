package es.codeurjc.books.domain.port;

public interface NotificationClient {

    public void send(FullBookDto message);

}