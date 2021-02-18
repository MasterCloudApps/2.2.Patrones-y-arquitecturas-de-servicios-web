package es.codeurjc.books.domain;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

	FullBookDto save(FullBookDto book);

	List<FullBookDto> findAllBooks();

	Optional<FullBookDto> findBookById(Long id);

}
