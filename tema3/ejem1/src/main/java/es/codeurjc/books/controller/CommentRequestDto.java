package es.codeurjc.books.controller;

public class CommentRequestDto {

	private String publishtext;

	private int punctuation;
	
	private String userNick;

	public CommentRequestDto() {
	}

	public CommentRequestDto(String userNick, int punctuation, String publishtext) {

		this.userNick = userNick;
		this.publishtext = publishtext;
		this.punctuation = punctuation;
	}

	public String getUserNick() {
		return userNick;
	}
	
	public void setUserNick(String userNick) {
		this.userNick = userNick;
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

}
