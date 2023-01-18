import es.codeurjc.books.domain.port.CommentDto;

public interface CommentRepository {
    
    public void save(CommentDto comment);

    public CommendDto findById(Long id);
}
