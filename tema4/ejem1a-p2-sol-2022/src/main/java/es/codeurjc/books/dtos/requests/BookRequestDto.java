package es.codeurjc.books.dtos.requests;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookRequestDto {

    @NotBlank(message = "Title is mandatory")
    private String title;
    private String summary;
    @NotBlank(message = "Author is mandatory")
    private String author;
    @NotBlank(message = "Publisher is mandatory")
    private String publisher;
    private int publicationYear;

}
