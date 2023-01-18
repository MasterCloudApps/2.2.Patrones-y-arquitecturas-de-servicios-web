package es.codeurjc.booksmanagementspring.dto;

import java.util.List;

public record BookDTO(
        long id,
        String title,
        String resume,
        String author,
        String publishing,
        String year,
        List<ReviewBasicDTO> reviews
)  {
}
