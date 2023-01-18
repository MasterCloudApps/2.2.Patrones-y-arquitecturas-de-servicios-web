package es.codeurjc.booksmanagementspring.dto;

public record BookCreateDTO(
        String title,
        String resume,
        String author,
        String publishing,
        String year
) {
}
