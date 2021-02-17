package es.codeurjc.books.model;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Book {

	public interface WithId {}
	public interface Basic extends WithId {}
	public interface WithComment {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(WithId.class)
	private Long id;

	@JsonView(Basic.class)
	private String title;

	@Column(columnDefinition = "TEXT")
	@JsonView(Basic.class)
	private String summary;

	@JsonView(Basic.class)
	private String author;

	@JsonView(Basic.class)
	private String editorial;

	@JsonView(Basic.class)
	private int publishYear;

	@JsonView(WithComment.class)
	@OneToMany(mappedBy="book", cascade = CascadeType.ALL, orphanRemoval = true)
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

	public Long getId() {
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

	public void addComment(Comment comment) {
		comments.add(comment);
		comment.setBook(this);
	}
	
	public void removeComment(Comment comment) {
		boolean removed = comments.remove(comment);
		if(!removed) {
			throw new NoSuchElementException();
		}
		comment.setBook(null);
	}

	@Override
	public String toString() {
		return String.format("Book [id=%s, title=%s, summary=%s, author=%s, editorial=%s, publishYear=%d", id, title,
				summary, author, editorial, publishYear);
	}

}
