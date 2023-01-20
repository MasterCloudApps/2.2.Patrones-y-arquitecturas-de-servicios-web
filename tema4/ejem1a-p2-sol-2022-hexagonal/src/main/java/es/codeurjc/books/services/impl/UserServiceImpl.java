package es.codeurjc.books.services.impl;

import static org.springframework.util.CollectionUtils.isEmpty;

import java.util.Collection;
import java.util.stream.Collectors;

import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import es.codeurjc.books.domain.port.UserDto;
import es.codeurjc.books.domain.port.UserUseCase;
import es.codeurjc.books.dtos.requests.UpdateUserEmailRequestDto;
import es.codeurjc.books.dtos.requests.UserRequestDto;
import es.codeurjc.books.dtos.responses.UserResponseDto;
import es.codeurjc.books.exceptions.UserCanNotBeDeletedException;
import es.codeurjc.books.exceptions.UserNotFoundException;
import es.codeurjc.books.exceptions.UserWithSameNickException;
import es.codeurjc.books.infrastructure.models.UserEntity;
import es.codeurjc.books.infrastructure.repositories.UserJpaRepository;
import es.codeurjc.books.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    private Mapper mapper;
    private UserUseCase userUseCase;

    public UserServiceImpl(Mapper mapper, UserUseCase userUseCase) {
        this.mapper = mapper;
        this.userUseCase = userUseCase;
    }

    public Collection<UserResponseDto> findAll() {
        return this.userUseCase.findAllUsers().stream()
                .map(user -> this.mapper.map(user, UserResponseDto.class))
                .collect(Collectors.toList());
    }

    public UserResponseDto save(UserRequestDto userRequestDto) {
        if (this.userUseCase.existsUserByNick(userRequestDto.getNick())) {
            throw new UserWithSameNickException();
        }
        UserEntity user = this.mapper.map(userRequestDto, UserEntity.class);
        return mapper.map(this.userUseCase.saveUser(mapper.map(user, UserDto.class)), UserResponseDto.class);
    }

    public UserResponseDto findById(long userId) {
        UserDto user = this.userUseCase.findUserById(userId).orElseThrow(UserNotFoundException::new);
        return this.mapper.map(user, UserResponseDto.class);
    }

    public UserResponseDto updateEmail(long userId, UpdateUserEmailRequestDto updateUserEmailRequestDto) {
        return this.mapper.map(this.userUseCase.updateEmail(userId, updateUserEmailRequestDto.getEmail()), UserResponseDto.class);
    }

    public UserResponseDto delete(long userId) {
        UserDto user = this.userUseCase.deleteUser(userId);
        return this.mapper.map(user, UserResponseDto.class);
    }

}
