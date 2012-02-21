package net.doudouer.domain;

import java.io.Serializable;

public class FilmReview implements Serializable{

	private static final long serialVersionUID = 1027552677116375445L;
	private Long id;
	private Long userID;
	private String content;
	private Long movieID;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserID() {
		return userID;
	}
	public void setUserID(Long userID) {
		this.userID = userID;
	}
	public Long getMovieID() {
		return movieID;
	}
	public void setMovieID(Long movieID) {
		this.movieID = movieID;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
