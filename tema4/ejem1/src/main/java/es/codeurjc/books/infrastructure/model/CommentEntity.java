package es.codeurjc.books.infrastructure.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class CommentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(columnDefinition = "TEXT")
	private String publishtext;

	private int punctuation;
	
	@ManyToOne
	private BookEntity book;
	
	@ManyToOne
	private UserEntity user;

	public CommentEntity() {
	}

	public CommentEntity(UserEntity user, int punctuation, String publishtext) {

		this.user = user;
		this.publishtext = publishtext;
		this.punctuation = punctuation;
	}

	public CommentEntity(CommentEntity comment) {
		this.id = comment.id;
		this.user = comment.user;
		this.publishtext = comment.publishtext;
		this.punctuation = comment.punctuation;
		this.book = comment.book;
	}

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public UserEntity getUser() {
		return user;
	}
	
	public void setUser(UserEntity user) {
		this.user = user;
	}

	public void setName(UserEntity user) {
		this.user = user;
	}

	public String getPublishtext() {
		return publishtext;
	}

	public void setPublishtext(String publishtext) {
		this.publishtext = publishtext;
	}

	public int getPunctuation() {
		return punctuation;
	}

	public void setPunctuation(int punctuation) {
		this.punctuation = punctuation;
	}

	public BookEntity getBook() {
		return book;
	}

	public void setBook(BookEntity book) {
		this.book = book;
	}	
}
