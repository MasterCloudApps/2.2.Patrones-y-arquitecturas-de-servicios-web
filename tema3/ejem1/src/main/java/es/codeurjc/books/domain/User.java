package es.codeurjc.books.domain;

public class User {

	private Long id;

	private String nick;

	private String mail;

	public User() {
	}

	public User(String nick, String mail) {
		this.nick = nick;
		this.mail = mail;
	}
}
