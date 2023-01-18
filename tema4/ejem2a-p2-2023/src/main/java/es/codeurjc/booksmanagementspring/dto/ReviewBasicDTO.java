package es.codeurjc.booksmanagementspring.dto;

public record ReviewBasicDTO(
        long id,
        String comment,
        int rate,
        long userId
) {
}
