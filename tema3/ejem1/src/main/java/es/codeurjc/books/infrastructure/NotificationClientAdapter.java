package es.codeurjc.books.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.codeurjc.books.domain.port.FullBookDto;
import es.codeurjc.books.domain.port.NotificationClient;

@Component
public class NotificationClientAdapter implements NotificationClient {

    @Autowired
    StreamBridge bridge;

    @Override
    public void send(FullBookDto message) {
        
        bridge.send("notifications", message);
    }
    
}
