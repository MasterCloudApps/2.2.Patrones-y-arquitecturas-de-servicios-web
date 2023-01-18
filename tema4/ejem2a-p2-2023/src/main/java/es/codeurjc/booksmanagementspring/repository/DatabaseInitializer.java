package es.codeurjc.booksmanagementspring.repository;

import es.codeurjc.booksmanagementspring.model.Book;
import es.codeurjc.booksmanagementspring.model.Review;
import es.codeurjc.booksmanagementspring.model.User;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer {
    private final BookRepository bookRepository;

    private final ReviewRepository reviewRepository;

    private final UserRepository userRepository;

    public DatabaseInitializer(BookRepository bookRepository, ReviewRepository reviewRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {
        User user1 = new User("Alex", "alex@gmail.com");
        User user2 = new User("Jesus", "jesus@gmail.com");

        Book book1 = new Book("Tomás Nevinson",
                "Dos hombres, uno en la ficción y otro en la realidad...",
                "Javier Marías",
                "Alfaguara",
                "2021");

        Book book2 = new Book("Los vencejos",
                "Una espléndida novela humanista sobre la dignidad y la esperanza",
                "Fernando Aramburu",
                "TUSQUETS EDITORES",
                "2021");

        Review review1 = new Review("Me ha gustado", 4, user1, book1);
        Review review2 = new Review("No me ha gustado", 1, user1, book2);
        Review review3 = new Review( "Un libro muy interesante", 3, user2, book1);
        Review review4 = new Review("Muy aburrido", 1, user2, book2);

        book1.getReviews().add(review1);
        book1.getReviews().add(review3);
        book2.getReviews().add(review2);
        book2.getReviews().add(review4);

        user1.getReviews().add(review1);
        user1.getReviews().add(review2);
        user2.getReviews().add(review3);
        user2.getReviews().add(review4);

        userRepository.save(user1);
        userRepository.save(user2);

        bookRepository.save(book1);
        bookRepository.save(book2);

        reviewRepository.save(review1);
        reviewRepository.save(review2);
        reviewRepository.save(review3);
        reviewRepository.save(review4);
    }
}
