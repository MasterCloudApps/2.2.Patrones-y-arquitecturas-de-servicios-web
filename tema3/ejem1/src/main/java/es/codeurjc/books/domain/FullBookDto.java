package es.codeurjc.books.domain;

import java.util.List;

public class FullBookDto {
	
	private Long id;
	
	private String title;

	private String summary;

	private String author;

	private String editorial;

	private int publishYear;
	
	private List<Comment> comments;
	
	public static FullBookDto fromBookDto(BookDto book) {
		return new FullBookDto(
				book.getTitle(),
				book.getSummary(),
				book.getAuthor(),
				book.getEditorial(),
				book.getPublishYear());
	}

	public FullBookDto() {
	}

	public FullBookDto(String title, String summary, String author, String editorial, int publishYear) {
		this(null, title, summary, author, editorial, publishYear);
	}
	
	public FullBookDto(Long id, String title, String summary, String author, String editorial, int publishYear) {
		this.id = id;
		this.title = title;
		this.summary = summary;
		this.author = author;
		this.editorial = editorial;
		this.publishYear = publishYear;		
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public int getPublishYear() {
		return publishYear;
	}

	public void setPublishYear(int publishYear) {
		this.publishYear = publishYear;
	}

}
