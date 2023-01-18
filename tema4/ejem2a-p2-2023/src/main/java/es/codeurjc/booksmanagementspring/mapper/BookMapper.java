package es.codeurjc.booksmanagementspring.mapper;

import es.codeurjc.booksmanagementspring.dto.BookBasicDTO;
import es.codeurjc.booksmanagementspring.dto.BookCreateDTO;
import es.codeurjc.booksmanagementspring.dto.BookDTO;
import es.codeurjc.booksmanagementspring.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring", uses = {ReviewMapper.class})
public interface BookMapper {

    BookDTO toDTO(Book book);

    Book toDomain(BookDTO bookDTO);

    Book toDomain(BookCreateDTO bookCreateDTO);

    List<BookDTO> toDTOs(Collection<Book> books);

    BookBasicDTO toBasicDTO(Book book);
}
