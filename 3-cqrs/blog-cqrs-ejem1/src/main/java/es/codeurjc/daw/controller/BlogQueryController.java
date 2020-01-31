package es.codeurjc.daw.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.daw.model.Post;
import es.codeurjc.daw.service.BlogQueryService;

@RestController
@RequestMapping("/api/post")
public class BlogQueryController {
	
	@Autowired
	private BlogQueryService blogService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/")
	public List<BasicPostDto> listPosts() {
		List<Post> posts = blogService.getPostsList();
		return posts.stream().map(post -> convertPostEntityToDto(post)).collect(Collectors.toList());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<FullPostDto> getPost(@PathVariable long id) {
		Post post = this.blogService.getPost(id).orElse(null);
		if (post == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(convertPostEntityToPostFullDto(post), HttpStatus.OK);
	}
	private FullPostDto convertPostEntityToPostFullDto(Post post) {
		return modelMapper.map(post, FullPostDto.class);
	}

	private BasicPostDto convertPostEntityToDto(Post post) {
		return modelMapper.map(post, BasicPostDto.class);
	}

}
