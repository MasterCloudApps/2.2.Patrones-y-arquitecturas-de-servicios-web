package es.codeurjc.books.domain;

public class Comment {

	private Long id;

	private String publishtext;

	private int punctuation;
	
	private Book book;
	
	private User user;

	public Comment() {
	}

	public Comment(User user, int punctuation, String publishtext) {

		this.user = user;
		this.publishtext = publishtext;
		this.punctuation = punctuation;
	}

	public Comment(Comment comment) {
		this.id = comment.id;
		this.user = comment.user;
		this.publishtext = comment.publishtext;
		this.punctuation = comment.punctuation;
		this.book = comment.book;
	}
}
