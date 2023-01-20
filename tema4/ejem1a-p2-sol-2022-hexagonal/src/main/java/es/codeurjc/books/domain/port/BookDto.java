package es.codeurjc.books.domain.port;

import java.util.ArrayList;
import java.util.List;

public class BookDto {
	
	private String title;

	private String summary;

	private String author;

	private String publisher;

	private int publishYear;

	public BookDto() {
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

	public BookDto(String title, String summary, String author, String editorial, int publishYear) {
		this.title = title;
		this.summary = summary;
		this.author = author;
		this.publisher = editorial;
		this.publishYear = publishYear;		
	}
	
}
