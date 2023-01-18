package es.codeurjc.booksmanagementspring.mapper;

import es.codeurjc.booksmanagementspring.dto.ReviewBasicDTO;
import es.codeurjc.booksmanagementspring.dto.ReviewCreateDTO;
import es.codeurjc.booksmanagementspring.dto.ReviewDTO;
import es.codeurjc.booksmanagementspring.model.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @Mapping(source = "username.id", target = "userId")
    @Mapping(source = "book.id", target = "bookId")
    ReviewDTO toDTO(Review review);

    List<ReviewDTO> toDTOs(Collection<Review> reviews);

    Review toDomain(ReviewDTO reviewDTO);

    Review toDomain(ReviewCreateDTO reviewDTO);

    @Mapping(source = "username.id", target = "userId")
    ReviewBasicDTO toBasicDTO(Review review);
}
