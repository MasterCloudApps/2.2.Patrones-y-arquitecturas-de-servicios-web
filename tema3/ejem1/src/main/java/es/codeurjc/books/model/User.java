package es.codeurjc.books.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class User {
	
	public interface Basic {}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(Basic.class)
	private Long id;

	@Column(unique = true)
	@JsonView(Basic.class)
	private String nick;

	@JsonView(Basic.class)
	private String mail;

	public User() {
	}

	public User(String nick, String mail) {
		this.nick = nick;
		this.mail = mail;
	}

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
}
