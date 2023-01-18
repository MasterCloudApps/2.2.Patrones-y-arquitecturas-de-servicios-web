package es.codeurjc.booksmanagementspring.controller;

import es.codeurjc.booksmanagementspring.dto.BookBasicDTO;
import es.codeurjc.booksmanagementspring.dto.BookCreateDTO;
import es.codeurjc.booksmanagementspring.dto.BookDTO;
import es.codeurjc.booksmanagementspring.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("/books")
public class BookRestController {
    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @Operation(summary = "Get all books")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Books found",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema( schema = @Schema(implementation = BookDTO.class))) })})
    @GetMapping("/")
    public ResponseEntity<Page<?>> getBooks(Pageable pageable, @RequestParam(required = false) String view) {
        if("basic".equals(view)){
            return ResponseEntity.ok(bookService.findAllBasic(pageable));
        } else {
            return ResponseEntity.ok(bookService.findAll(pageable));
        }
    }

    @Operation(summary = "Get a book by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the book",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BookDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookDetail(@PathVariable long id) {
        return ResponseEntity.ok(bookService.findByIdDTO(id));
    }

    @Operation(summary = "Create new book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Book created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BookDTO.class)) })})
    @PostMapping("/")
    public ResponseEntity<BookDTO> createBook(@RequestBody BookCreateDTO bookCreateDTO) {
        BookDTO bookDTO = bookService.save(bookCreateDTO);
        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(bookDTO.id()).toUri();
        return ResponseEntity.created(location).body(bookDTO);
    }

    @Operation(summary = "Update a book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BookDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content) })
    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> replaceBook(@RequestBody BookCreateDTO newBook, @PathVariable long id) {
        return ResponseEntity.ok(bookService.replace(newBook, id));
    }

    @Operation(summary = "Delete a book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book deleted",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BookDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content) })
    @DeleteMapping("/{id}")
    public ResponseEntity<BookDTO> deleteBook( @PathVariable long id) {
        return ResponseEntity.ok(bookService.delete(id));
    }

}