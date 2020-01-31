package es.codeurjc.daw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.daw.service.BlogCommandService;

@RestController
@RequestMapping("/api/post")
public class BlogCommandController {
	
	@Autowired
	private BlogCommandService blogService;
	
	@PostMapping("/")
	public void newPost(@RequestBody FullPostDto post) {
		this.blogService.addPost(post);
	}
	
	@PostMapping("/{postId}/comment")
	public void newComment(@PathVariable long postId, @RequestBody CommentDto comment) {
		this.blogService.addComment(postId, comment);
	}

	@DeleteMapping("/{postId}/comment/{commentId}")
	public void deleteComment(@PathVariable long postId, @PathVariable long commentId) {
		this.blogService.deleteComment(postId, commentId);
	}

}
