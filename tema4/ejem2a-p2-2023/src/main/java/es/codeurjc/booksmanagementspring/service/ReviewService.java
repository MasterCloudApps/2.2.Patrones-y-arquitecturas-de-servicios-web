package es.codeurjc.booksmanagementspring.service;

import es.codeurjc.booksmanagementspring.dto.ReviewCreateDTO;
import es.codeurjc.booksmanagementspring.dto.ReviewDTO;
import es.codeurjc.booksmanagementspring.mapper.ReviewMapper;
import es.codeurjc.booksmanagementspring.model.Review;
import es.codeurjc.booksmanagementspring.repository.ReviewRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private final ReviewMapper reviewMapper;
    private final ReviewRepository reviewRepository;

    private final BookService bookService;
    private final UserService userService;

    public ReviewService(ReviewRepository reviewRepository, ReviewMapper reviewMapper, BookService bookService, UserService userService) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
        this.bookService = bookService;
        this.userService = userService;
    }

    public ReviewDTO save(ReviewCreateDTO reviewCreateDTO) {
        Review review = reviewMapper.toDomain(reviewCreateDTO);
        review.setBook(bookService.findById(reviewCreateDTO.bookId()));
        review.setUsername(userService.findById(reviewCreateDTO.userId()));
        return reviewMapper.toDTO(reviewRepository.save(review));
    }

    public Page<ReviewDTO> findAll(Pageable pageable) {
        return reviewRepository.findAll(pageable).map(reviewMapper::toDTO);
    }

    public ReviewDTO findById(long id) {
        return reviewMapper.toDTO(reviewRepository.findById(id).orElseThrow());
    }

    public ReviewDTO replace(ReviewCreateDTO reviewDTO, long id) {
        Review newReview = reviewMapper.toDomain(reviewDTO);
        Review oldReview = reviewRepository.findById(id).orElseThrow();
        newReview.setId(id);
        newReview.setBook(oldReview.getBook());
        newReview.setUsername(oldReview.getUsername());
        reviewRepository.save(newReview);
        return reviewMapper.toDTO(newReview);
    }

    public ReviewDTO delete(long id) {
        Review review = this.reviewRepository.findById(id).orElseThrow();
        reviewRepository.delete(review);
        return reviewMapper.toDTO(review);
    }
}
