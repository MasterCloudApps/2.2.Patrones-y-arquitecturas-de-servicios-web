package es.codeurjc.books.infrastructure;

import java.util.List;
import java.util.stream.Collectors;

import org.dozer.Mapper;

import es.codeurjc.books.domain.port.CommentDto;
import es.codeurjc.books.domain.port.CommentRepository;
import es.codeurjc.books.infrastructure.models.CommentEntity;
import es.codeurjc.books.infrastructure.repositories.CommentJpaRepository;

public class CommentRepositoryAdapter implements CommentRepository {
    
    CommentJpaRepository commentRepository;
    Mapper mapper;

    public CommentRepositoryAdapter(CommentJpaRepository repo, Mapper mapper) {
        this.commentRepository = repo;
        this.mapper = mapper;
    }

    public CommentDto save(CommentDto comment) {
        CommentEntity commentEntity = mapper.map(comment, CommentEntity.class);
        CommentEntity savedEntity = commentRepository.save(commentEntity);
        return mapper.map(savedEntity, CommentDto.class);
    }

    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }

	@Override
	public CommentDto findById(Long id) {
		CommentEntity commentEntity = commentRepository.findById(id).orElseThrow();
		return mapper.map(commentEntity, CommentDto.class);
	}

	@Override
	public List<CommentDto> findByUserId(Long id) {
		return commentRepository
				.findByUserId(id)
				.stream()
				.map(c->mapper.map(c, CommentDto.class))
				.collect(Collectors.toList());
	}

}
