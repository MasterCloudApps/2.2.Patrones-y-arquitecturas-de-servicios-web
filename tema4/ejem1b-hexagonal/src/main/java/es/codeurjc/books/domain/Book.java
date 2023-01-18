package es.codeurjc.books.domain;

import java.util.ArrayList;
import java.util.List;

public class Book {
	private Long id;

	private String title;

	private String summary;

	private String author;

	private String editorial;

	private int publishYear;

	private List<Comment> comments;

	public Book() {
	}

	public Book(String title, String summary, String author, String editorial, int publishYear) {
		this(title, summary, author, editorial, publishYear, new ArrayList<>());
	}
	
	public Book(String title, String summary, String author, String editorial, int publishYear, List<Comment> comments) {
		this.title = title;
		this.summary = summary;
		this.author = author;
		this.editorial = editorial;
		this.publishYear = publishYear;		
		this.comments = comments;
	}

	public void removeComment(Comment comment) {
		this.comments.remove(comment);
	}
}
