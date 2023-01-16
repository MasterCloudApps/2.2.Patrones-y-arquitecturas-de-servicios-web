package es.codeurjc.books.domain.port;

public class UserDto {

	private String nick;

	private String mail;

	public UserDto() {
	}

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }
}
