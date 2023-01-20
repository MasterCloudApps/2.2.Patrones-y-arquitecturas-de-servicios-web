package es.codeurjc.books.infrastructure;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.dozer.Mapper;

import es.codeurjc.books.domain.port.UserDto;
import es.codeurjc.books.domain.port.UserRepository;
import es.codeurjc.books.infrastructure.models.UserEntity;
import es.codeurjc.books.infrastructure.repositories.UserJpaRepository;

public class UserRepositoryAdapter implements UserRepository {
	
	private UserJpaRepository userRepository;
	private Mapper mapper;

	public UserRepositoryAdapter(UserJpaRepository userRepository, Mapper mapper) {
		this.userRepository = userRepository;
		this.mapper = mapper;
	}

	@Override
	public Collection<UserDto> findAll() {
		return userRepository
				.findAll()
				.stream()
				.map(u -> mapper.map(u, UserDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public boolean existsUserByNick(String nick) {
		return userRepository.existsByNick(nick);
	}

	@Override
	public UserDto save(UserDto user) {
		return mapper.map(
				this.userRepository.save(
						mapper.map(user, 
								UserEntity.class)), 
				UserDto.class);
	}

	@Override
	public Optional<UserDto> findById(long userId) {
		return userRepository.findById(userId).map(u -> mapper.map(u, UserDto.class));
	}

	@Override
	public void deleteUser(Long userId) {
		this.userRepository.deleteById(userId);
	}

}
