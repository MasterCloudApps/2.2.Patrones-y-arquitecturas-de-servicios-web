package es.codeurjc.books.controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.books.dtos.requests.BookRequestDto;
import es.codeurjc.books.dtos.requests.CommentRequestDto;
import es.codeurjc.books.dtos.responses.BookDetailsResponseDto;
import es.codeurjc.books.dtos.responses.BookResponseDto;
import es.codeurjc.books.dtos.responses.CommentResponseDto;
import es.codeurjc.books.services.BookService;
import es.codeurjc.books.services.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private BookService bookService;
    private CommentService commentService;

    public BookController(BookService bookService, CommentService commentService) {
        this.bookService = bookService;
        this.commentService = commentService;
    }

    @Operation(summary = "Get all books")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all books",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = BookResponseDto.class)))})})
    @GetMapping("/")
    public Collection<BookResponseDto> getBooks() {
        return this.bookService.findAll();
    }

    @Operation(summary = "Get a book by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the book",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BookDetailsResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid format id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content)})
    @GetMapping("/{bookId}")
    public BookDetailsResponseDto getBook(@Parameter(description = "id of book to be searched")
                                          @PathVariable long bookId) {
        return this.bookService.findById(bookId);
    }

    @Operation(summary = "Create a new book")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Book to be created", required = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = BookRequestDto.class)))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created the book",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BookDetailsResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid book attributes supplied",
                    content = @Content)})
    @PostMapping("/")
    public BookDetailsResponseDto createBook(@Valid @RequestBody BookRequestDto bookRequestDto) {
        return this.bookService.save(bookRequestDto);
    }

    @Operation(summary = "Add a comment to a book")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "comment to be added", required = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CommentRequestDto.class)))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Added comment to the book",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid comment attributes supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Book or user not found",
                    content = @Content)})
    @PostMapping("/{bookId}/comments/")
    public CommentResponseDto createComment(@Parameter(description = "identifier of the book to which the comment will be added")
                                            @PathVariable long bookId,
                                            @Valid @RequestBody CommentRequestDto commentRequestDto) {
        return this.commentService.addComment(bookId, commentRequestDto);
    }

    @Operation(summary = "Delete a comment from a book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted comment from the book",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid format id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Comment not found with passed bookId and commentId",
                    content = @Content)})
    @DeleteMapping("/{bookId}/comments/{commentId}")
    public CommentResponseDto deleteComment(@Parameter(description = "identifier of the book to which the comment belongs")
                                            @PathVariable long bookId,
                                            @Parameter(description = "id of comment to be deleted")
                                            @PathVariable long commentId) {
        return this.commentService.deleteComment(bookId, commentId);
    }

}
