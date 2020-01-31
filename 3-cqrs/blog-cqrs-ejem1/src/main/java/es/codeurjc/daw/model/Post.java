package es.codeurjc.daw.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id = -1;

	private String title;

	private String content;

	@OneToMany
	private List<Comment> comments = new ArrayList<>();

	public Post(String title, String content) {
		this.title = title;
		this.content = content;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void deleteComment(Comment comment) {
		this.comments.remove(comment);
	}

	@Override
	public String toString() {
		return this.title + " (" + comments.size() + " comments)";
	}

}
