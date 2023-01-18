package es.codeurjc.booksmanagementspring.controller;

import es.codeurjc.booksmanagementspring.dto.ReviewCreateDTO;
import es.codeurjc.booksmanagementspring.dto.ReviewDTO;
import es.codeurjc.booksmanagementspring.service.ReviewService;
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
@RequestMapping("/reviews")
public class ReviewRestController {
    private final ReviewService reviewService;

    public ReviewRestController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Operation(summary = "Get all reviews")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "reviews found",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema( schema = @Schema(implementation = ReviewDTO.class))) })})
    @GetMapping("/")
    public ResponseEntity<Page<ReviewDTO>> getReviews(Pageable pageable) {
        return ResponseEntity.ok(reviewService.findAll(pageable));
    }

    @Operation(summary = "Get a review by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the review",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReviewDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "review not found",
                    content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<ReviewDTO> getReviewById(@PathVariable long id) {
        return ResponseEntity.ok(reviewService.findById(id));
    }

    @Operation(summary = "Create new review")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "review created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReviewDTO.class)) })})
    @PostMapping("/")
    public ResponseEntity<ReviewDTO> createReview(@RequestBody ReviewCreateDTO reviewCreateDTO) {
        ReviewDTO reviewDTO = reviewService.save(reviewCreateDTO);
        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(reviewDTO.id()).toUri();
        return ResponseEntity.created(location).body(reviewDTO);
    }

    @Operation(summary = "Update a review")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "review updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReviewDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "review not found",
                    content = @Content) })
    @PutMapping("/{id}")
    public ResponseEntity<ReviewDTO> replaceReview(@RequestBody ReviewCreateDTO reviewDTO, @PathVariable long id) {
        return ResponseEntity.ok(reviewService.replace(reviewDTO, id));
    }

    @Operation(summary = "Delete a review")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "review deleted",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReviewDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "review not found",
                    content = @Content) })
    @DeleteMapping("/{id}")
    public ResponseEntity<ReviewDTO> deleteReview(@PathVariable long id) {
        return ResponseEntity.ok(reviewService.delete(id));
    }

}
