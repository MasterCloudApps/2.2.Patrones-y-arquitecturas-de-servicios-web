package es.codeurjc.daw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.daw.service.BlogQueryService;

@RestController
@RequestMapping("/api/post")
public class BlogQueryController {
	
	@Autowired
	private BlogQueryService blogService;
	
	@GetMapping("/")
	public List<BasicPostDto> listPosts() {
		return blogService.getPostsList();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<FullPostDto> getPost(@PathVariable long id) {
		FullPostDto post = this.blogService.getPost(id);
		if (post == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(post, HttpStatus.OK);
	}

}
