package es.codeurjc.daw.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.codeurjc.daw.model.Post;
import es.codeurjc.daw.repository.PostRepository;

@Service
public class BlogQueryService {

	@Autowired
	private PostRepository postRepository;
	
	public List<Post> getPostsList() {
		return postRepository.findAll();
	}

	public Optional<Post> getPost(long id) {
		return postRepository.findById(id);
	}

}
