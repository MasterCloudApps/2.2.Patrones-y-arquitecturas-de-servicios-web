package es.codeurjc.books.dtos.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UserRequestDto {

    @NotBlank(message = "Nick is mandatory")
    private String nick;
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Invalid email")
    private String email;

}
