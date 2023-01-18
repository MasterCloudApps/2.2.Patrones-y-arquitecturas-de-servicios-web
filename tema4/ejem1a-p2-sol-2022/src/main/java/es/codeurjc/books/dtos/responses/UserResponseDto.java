package es.codeurjc.books.dtos.responses;

import lombok.Data;

@Data
public class UserResponseDto {

    private Long id;
    private String nick;
    private String email;

}
