package es.codeurjc.books.domain.port;

public class CommentDto {
    private Long id;

	private String publishtext;

	private int punctuation;
	
	private UserDto user;

	public CommentDto() {
	}

	public Long getId() {
		return id;
	}
}
