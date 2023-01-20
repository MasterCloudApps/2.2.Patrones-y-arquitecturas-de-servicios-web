package es.codeurjc.books.domain;

import static org.springframework.util.CollectionUtils.isEmpty;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.dozer.Mapper;

import es.codeurjc.books.domain.port.CommentDto;
import es.codeurjc.books.domain.port.CommentRepository;
import es.codeurjc.books.domain.port.UserDto;
import es.codeurjc.books.domain.port.UserRepository;
import es.codeurjc.books.domain.port.UserUseCase;
import es.codeurjc.books.exceptions.UserCanNotBeDeletedException;
import es.codeurjc.books.exceptions.UserNotFoundException;
import es.codeurjc.books.infrastructure.models.UserEntity;

public class UserUseCaseImpl implements UserUseCase {
	
	private UserRepository userRepository;
	private Mapper mapper;
	private CommentRepository commentRepository;

	public UserUseCaseImpl(UserRepository userRepository, CommentRepository commentRepository, Mapper mapper) {
		this.userRepository = userRepository;
		this.commentRepository = commentRepository;
		this.mapper = mapper;
	}

	@Override
	public Collection<UserDto> findAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public boolean existsUserByNick(String nick) {
		return userRepository.existsUserByNick(nick);
	}

	@Override
	public UserDto saveUser(UserDto user) {
		return userRepository.save(user);
	}

	@Override
	public Optional<UserDto> findUserById(long userId) {
		return userRepository.findById(userId);
	}

	@Override
	public UserDto deleteUser(Long userId) {
		List<CommentDto> comments = this.commentRepository.findByUserId(userId);
        if (!comments.isEmpty()) {
            throw new UserCanNotBeDeletedException();
        }

        UserDto user = userRepository.findById(userId).orElseThrow();
		userRepository.deleteUser(userId);
		
		return user;
	}

	@Override
	public UserDto updateEmail(long userId, String email) {
        UserDto user = this.userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        if (!user.getEmail().equalsIgnoreCase(email)) {
            user.setEmail(email);
            user = this.userRepository.save(user);
        }
        return user;
	}

}
