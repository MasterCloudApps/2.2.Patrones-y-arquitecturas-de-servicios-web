package es.codeurjc.books.domain.port;

import java.util.Collection;
import java.util.Optional;

public interface UserUseCase {

	Collection<UserDto> findAllUsers();

	boolean existsUserByNick(String nick);

	UserDto saveUser(UserDto user);

	Optional<UserDto> findUserById(long userId);

	UserDto deleteUser(Long userId);

	UserDto updateEmail(long userId, String email);

}
