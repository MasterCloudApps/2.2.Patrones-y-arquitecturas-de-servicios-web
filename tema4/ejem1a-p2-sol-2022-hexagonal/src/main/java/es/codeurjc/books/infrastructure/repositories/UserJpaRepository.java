package es.codeurjc.books.infrastructure.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.books.infrastructure.models.UserEntity;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByNick(String nick);

    Optional<UserEntity> findByNick(String nick);

}
