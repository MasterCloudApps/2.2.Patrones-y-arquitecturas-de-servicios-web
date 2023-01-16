package es.codeurjc.books.controller;

import es.codeurjc.books.domain.port.FullBookDto;

public class BookResponseDto {

	private long id;
	
	private String title;

	private String summary;

	private String author;

	private String editorial;

	private int publishYear;
	
	public static BookResponseDto fromFullBookDto(FullBookDto fullBookDto) {
		return new BookResponseDto(
				fullBookDto.getId(),
				fullBookDto.getTitle(),
				fullBookDto.getSummary(),
				fullBookDto.getAuthor(),
				fullBookDto.getEditorial(),
				fullBookDto.getPublishYear());
	}

	public BookResponseDto() {
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

	public BookResponseDto(long id, String title, String summary, String author, String editorial, int publishYear) {
		this.id = id;
		this.title = title;
		this.summary = summary;
		this.author = author;
		this.editorial = editorial;
		this.publishYear = publishYear;		
	}

}
