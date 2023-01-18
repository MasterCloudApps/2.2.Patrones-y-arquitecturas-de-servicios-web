package es.codeurjc.booksmanagementspring.service;

import es.codeurjc.booksmanagementspring.dto.ReviewDTO;
import es.codeurjc.booksmanagementspring.dto.UserCreateDTO;
import es.codeurjc.booksmanagementspring.dto.UserDTO;
import es.codeurjc.booksmanagementspring.exceptions.UserHasReviewsException;
import es.codeurjc.booksmanagementspring.mapper.ReviewMapper;
import es.codeurjc.booksmanagementspring.mapper.UserMapper;
import es.codeurjc.booksmanagementspring.model.Review;
import es.codeurjc.booksmanagementspring.model.User;
import es.codeurjc.booksmanagementspring.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final ReviewMapper reviewMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper, ReviewMapper reviewMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.reviewMapper = reviewMapper;
    }

    public UserDTO save(UserCreateDTO userDTO) {
        return userMapper.toDTO(userRepository.save(userMapper.toDomain(userDTO)));
    }

    public Page<UserDTO> findAll(Pageable pageable) {
        return userRepository.findAll(pageable).map(userMapper::toDTO);
    }

    public Page<ReviewDTO> findAllByUserId(long id) {
        List<Review> reviews = userRepository.findById(id).orElseThrow().getReviews();
        return new PageImpl<>(reviewMapper.toDTOs(reviews));
    }

    public UserDTO findByIdDTO(long id) {
        return userMapper.toDTO(userRepository.findById(id).orElseThrow());
    }

    public User findById(long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public UserDTO replace(UserCreateDTO userDTO, long id) {
        User newUser = userMapper.toDomain(userDTO);
        newUser.setId(id);
        userRepository.findById(newUser.getId()).orElseThrow();
        userRepository.save(newUser);
        return userMapper.toDTO(newUser);
    }

    public UserDTO delete(long id) throws UserHasReviewsException {
        User user = this.findById(id);
        if (this.userHasReviews(user)){
            throw new UserHasReviewsException("User has reviews so it can't be deleted");
        }
        userRepository.delete(user);
        return userMapper.toDTO(user);
    }

    private boolean userHasReviews(User user) {
        return !user.getReviews().isEmpty();
    }
}
