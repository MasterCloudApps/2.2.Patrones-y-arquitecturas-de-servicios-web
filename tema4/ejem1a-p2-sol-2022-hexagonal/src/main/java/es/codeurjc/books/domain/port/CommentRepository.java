package es.codeurjc.books.domain.port;

import java.util.List;

public interface CommentRepository {
    
    public CommentDto save(CommentDto comment);

    public CommentDto findById(Long id);
    
    public List<CommentDto> findByUserId(Long id);
    
    public void deleteById(Long id);
}
