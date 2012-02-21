package net.doudouer.domain;

import java.io.Serializable;

public class UserGibberish implements Serializable{

	private static final long serialVersionUID = -5509614854821383650L;
	
	private Long id;
	private Long userID;
	private String content;
	private Long timestamp;
	
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
}
