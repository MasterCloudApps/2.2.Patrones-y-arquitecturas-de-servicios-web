package es.codeurjc.booksmanagementspring.service;

import es.codeurjc.booksmanagementspring.dto.BookBasicDTO;
import es.codeurjc.booksmanagementspring.dto.BookCreateDTO;
import es.codeurjc.booksmanagementspring.dto.BookDTO;
import es.codeurjc.booksmanagementspring.mapper.BookMapper;
import es.codeurjc.booksmanagementspring.model.Book;
import es.codeurjc.booksmanagementspring.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private final BookMapper bookMapper;
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public BookDTO save(BookCreateDTO bookCreateDTO) {
        return bookMapper.toDTO(bookRepository.save(bookMapper.toDomain(bookCreateDTO)));
    }

    public Page<BookDTO> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable).map(bookMapper::toDTO);
    }

    public Page<BookBasicDTO> findAllBasic(Pageable pageable) {
        return bookRepository.findAll(pageable).map(bookMapper::toBasicDTO);
    }

    public BookDTO findByIdDTO(long id) {
        return bookMapper.toDTO(bookRepository.findById(id).orElseThrow());
    }

    public Book findById(long id) {
        return bookRepository.findById(id).orElseThrow();
    }

    public BookDTO replace(BookCreateDTO bookDTO, long id) {
        Book newBook = bookMapper.toDomain(bookDTO);
        newBook.setId(id);
        Book oldBook = bookRepository.findById(newBook.getId()).orElseThrow();
        oldBook.getReviews().forEach(review -> newBook.getReviews().add(review));
        bookRepository.save(newBook);
        return bookMapper.toDTO(newBook);
    }

    public BookDTO delete(long id) {
        Book book = findById(id);
        bookRepository.deleteById(id);
        return bookMapper.toDTO(book);
    }
}
