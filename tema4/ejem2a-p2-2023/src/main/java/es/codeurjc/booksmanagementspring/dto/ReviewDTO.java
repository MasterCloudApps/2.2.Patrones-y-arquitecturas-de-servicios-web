package es.codeurjc.booksmanagementspring.dto;

public record ReviewDTO(
        long id,
        String comment,
        int rate,
        long userId,
        long bookId
) {
}
