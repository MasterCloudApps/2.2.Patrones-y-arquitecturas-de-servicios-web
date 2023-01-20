package es.codeurjc.books.domain;

import es.codeurjc.books.domain.port.CommentUseCase;
import es.codeurjc.books.domain.port.FullBookDto;

import org.dozer.Mapper;
import org.webjars.NotFoundException;

import es.codeurjc.books.domain.port.BookRepository;
import es.codeurjc.books.domain.port.CommentDto;
import es.codeurjc.books.domain.port.CommentRepository;

public class CommentUseCaseImpl implements CommentUseCase {
    
    BookRepository bookRepository;
    CommentRepository commentRepository;
    Mapper mapper;

    public CommentUseCaseImpl(BookRepository bookRepository, CommentRepository commentRepository, Mapper mapper) {
        this.bookRepository = bookRepository;
        this.commentRepository = commentRepository;
        this.mapper = mapper;
    }

    public CommentDto save(CommentDto comment, Long bookId) {
        FullBookDto book = bookRepository.findBookById(bookId).orElseThrow();
        
        Book domainBook = mapper.map(book, Book.class);
        
		CommentDto savedComment = commentRepository.save(comment);
		
		domainBook.addComment(mapper.map(savedComment, Comment.class));

		bookRepository.save(mapper.map(domainBook, FullBookDto.class));

		return savedComment;
    }

	@Override
	public void delete(Long bookId, Long commentId) {
        CommentDto commentDto = commentRepository.findById(commentId);
		FullBookDto bookDto = bookRepository.findBookById(bookId).orElseThrow();

        Book book = mapper.map(bookDto, Book.class);
        Comment comment = mapper.map(commentDto, Comment.class);

		book.removeComment(comment);

		bookRepository.save(mapper.map(book, FullBookDto.class));
		commentRepository.deleteById(commentId);
	}

}
