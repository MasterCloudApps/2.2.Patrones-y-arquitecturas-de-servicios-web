package es.codeurjc.books.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.books.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByNick(String nick);

}