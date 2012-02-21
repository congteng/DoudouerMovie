package net.doudouer.domain;

import java.io.Serializable;

public class MoviePreference implements Serializable{

	private static final long serialVersionUID = -7357064137875029443L;
	
	private Long userID;
	private Long movieID;
	private double preference;
	private Long timestamp;
	
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	public Long getUserID() {
		return userID;
	}
	public void setUserID(Long userID) {
		this.userID = userID;
	}
	public double getPreference() {
		return preference;
	}
	public void setPreference(double preference) {
		this.preference = preference;
	}
	public Long getMovieID() {
		return movieID;
	}
	public void setMovieID(Long movieID) {
		this.movieID = movieID;
	}
}
