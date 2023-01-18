package es.codeurjc.booksmanagementspring.controller;

import es.codeurjc.booksmanagementspring.dto.ReviewDTO;
import es.codeurjc.booksmanagementspring.dto.UserCreateDTO;
import es.codeurjc.booksmanagementspring.dto.UserDTO;
import es.codeurjc.booksmanagementspring.exceptions.UserHasReviewsException;
import es.codeurjc.booksmanagementspring.service.UserService;
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
@RequestMapping("/users")
public class UserRestController {
    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Get all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "users found",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema( schema = @Schema(implementation = UserDTO.class))) })})
    @GetMapping("/")
    public ResponseEntity<Page<UserDTO>> getUsers(Pageable pageable) {
        return ResponseEntity.ok(userService.findAll(pageable));
    }

    @Operation(summary = "Get all reviews by user id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "reviews from user found",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema( schema = @Schema(implementation = ReviewDTO.class))) }),
            @ApiResponse(responseCode = "404", description = "user not found",
                    content = @Content)})
    @GetMapping("/{id}/reviews")
    public ResponseEntity<Page<ReviewDTO>> getReviewsByUserId(@PathVariable long id) {
        return ResponseEntity.ok(userService.findAllByUserId(id));
    }

    @Operation(summary = "Get a user by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the user",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "user not found",
                    content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserDetail(@PathVariable long id) {
        return ResponseEntity.ok(userService.findByIdDTO(id));
    }

    @Operation(summary = "Create new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "user created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class)) })})
    @PostMapping("/")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserCreateDTO userCreateDTO) {
        UserDTO userDTO = userService.save(userCreateDTO);
        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(userDTO.id()).toUri();
        return ResponseEntity.created(location).body(userDTO);
    }

    @Operation(summary = "Update a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "user updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "user not found",
                    content = @Content) })
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> replaceUser(@RequestBody UserCreateDTO userDTO, @PathVariable long id) {
        return ResponseEntity.ok(userService.replace(userDTO, id));
    }

    @Operation(summary = "Delete a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "user deleted",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "user has reviews, it can't be deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "user not found",
                    content = @Content) })
    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> deleteUser( @PathVariable long id) throws UserHasReviewsException {
        return ResponseEntity.ok(userService.delete(id));
    }

}
