package es.codeurjc.booksmanagementspring.dto;

public record UserDTO(
        long id,
        String nick,
        String email
) {
}
