package es.codeurjc.books.infrastructure;

import es.codeurjc.books.infrastructure.model.CommentEntity;
import es.codeurjc.books.infrastructure.repository.CommentJpaRepository;

public class CommentRepositoryAdapter implements CommentRepository {
    
    CommentJpaRepository commentRepository;
    ModelMapper mapper = new ModelMapper();

    public CommentRepositoryAdapter(CommentJpaRepository repo) {
        this.commentRepository = repo;
    }

    public void save(CommentDto comment) {
        CommentEntity commentEntity = mapper.map(comment, CommentEntity.class);
        commentRepository.save(commentEntity);
    }

    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }

}
