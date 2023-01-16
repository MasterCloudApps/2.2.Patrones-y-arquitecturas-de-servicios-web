package es.codeurjc.books.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.books.infrastructure.model.UserEntity;
import es.codeurjc.books.infrastructure.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository users;

	public void save(UserEntity user) {
		users.save(user);
	}

	public void replace(UserEntity updatedUser) {
		users.findById(updatedUser.getId()).orElseThrow();
		users.save(updatedUser);
	}

	public List<UserEntity> findAll() {
		return users.findAll();
	}

	public Optional<UserEntity> findById(long id) {
		return users.findById(id);
	}
	
	public boolean existsById(long id) {
		return users.existsById(id);
	}

	public void deleteById(long id) {
		users.deleteById(id);
	}

	public Optional<UserEntity> findByNick(String nick) {
		return users.findByNick(nick);
	}
}
