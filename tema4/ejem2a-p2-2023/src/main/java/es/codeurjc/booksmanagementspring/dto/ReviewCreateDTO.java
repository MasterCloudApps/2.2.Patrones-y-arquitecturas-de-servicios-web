package es.codeurjc.booksmanagementspring.dto;

public record ReviewCreateDTO(
        String comment,
        int rate,
        long userId,
        long bookId
) {
}
