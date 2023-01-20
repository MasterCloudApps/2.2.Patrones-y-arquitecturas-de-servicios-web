package es.codeurjc.books.domain.port;

import java.util.Collection;
import java.util.Optional;

public interface UserRepository {

	Collection<UserDto> findAll();

	boolean existsUserByNick(String nick);

	UserDto save(UserDto user);

	Optional<UserDto> findById(long userId);

	void deleteUser(Long userId);

}
