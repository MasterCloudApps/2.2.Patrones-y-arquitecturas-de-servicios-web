package es.codeurjc.books.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.books.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByNick(String nick);

    Optional<User> findByNick(String nick);

}
