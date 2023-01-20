package es.codeurjc.books.domain.port;

public interface CommentUseCase {
    
    public CommentDto save(CommentDto comment, Long bookId);

    public void delete(Long bookId, Long commentId);


}
