package es.codeurjc.books.domain.port;

import java.util.Collection;
import java.util.Optional;

public interface BookUseCase {
	
	public FullBookDto createBook(BookDto book);

	public Collection<FullBookDto> findAllBooks();

	public Optional<FullBookDto> findBookById(Long id);

}
