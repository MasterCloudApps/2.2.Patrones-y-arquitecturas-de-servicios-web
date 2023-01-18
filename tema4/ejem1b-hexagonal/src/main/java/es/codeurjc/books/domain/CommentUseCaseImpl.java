package es.codeurjc.books.domain;

import es.codeurjc.books.domain.port.CommentUseCase;
import es.codeurjc.books.domain.port.FullBookDto;
import es.codeurjc.books.domain.port.CommentDto;

public class CommentUseCaseImpl implements CommentUseCase {
    
    BookRepository bookRepository;
    CommentRepository commentRepository;

    public CommentUseCaseImpl(BookRepository bookRepository, CommentRepository commentRepository) {
        this.bookRepository = bookRepository;
        this.commentRepository = commentRepository;
    }

    public void save(CommentDto comment, Long bookId) {
        FullBookDto book = bookRepository.findById(bookId);
        
		book.addComment(comment);

		commentRepository.save(comment);
		bookRepository.save(book);

    }

    public void deleteCommentById(Long bookId, Long commentId) {
        CommentDto commentDto = commentRepository.findById(commentId);
		FullBookDto bookDto = bookRepository.findById(bookId).orElseThrow();

        Book book = mapper.map(bookDto, Book.class);
        Comment comment = mapper.map(commentDto, Comment.class);

		book.removeComment(comment);

		bookRepository.save(book);
		commentRepository.deleteById(comment.getId());

    }

}
