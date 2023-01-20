package es.codeurjc.books.domain.port;

import java.util.List;

public class FullBookDto {
	
	private Long id;
	
	private String title;

	private String summary;

	private String author;

	private String publisher;

	private int publishYear;
	
	private List<CommentDto> comments;
	
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
		this.publisher= editorial;
		this.publishYear = publishYear;		
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public List<CommentDto> getComments() {
		return comments;
	}

	public void setComments(List<CommentDto> comments) {
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

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String editorial) {
		this.publisher = editorial;
	}

	public int getPublishYear() {
		return publishYear;
	}

	public void setPublishYear(int publishYear) {
		this.publishYear = publishYear;
	}

}
