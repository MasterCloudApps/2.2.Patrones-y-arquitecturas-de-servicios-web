package es.codeurjc.daw.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.codeurjc.daw.model.Post;

public interface PostRepository extends JpaRepository<Post, Long>{

}
