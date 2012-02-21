package net.doudouer.domain;

import java.io.Serializable;
import java.util.Set;

public class Movie implements Serializable{

	private static final long serialVersionUID = -5842279468223433367L;
	
	private Long id;
	private String movieName;
	private String type;
	private String director;
	private String actor;
	private String description;
	private String country;
	private String language;
	private String releaseYear;
	
	private String avatarLink;
	
	/** 被谁收藏 **/
	private Set<User> whoCollect;
	
	public Set<User> getWhoCollect() {
		return whoCollect;
	}
	public void setWhoCollect(Set<User> whoCollect) {
		this.whoCollect = whoCollect;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getActor() {
		return actor;
	}
	public void setActor(String actor) {
		this.actor = actor;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getReleaseYear() {
		return releaseYear;
	}
	public void setReleaseYear(String releaseYear) {
		this.releaseYear = releaseYear;
	}
	public String getAvatarLink() {
		return avatarLink;
	}
	public void setAvatarLink(String avatarLink) {
		this.avatarLink = avatarLink;
	}
	@Override
	public String toString() {
		return this.actor  + " : " + this.description;
	}
	
}
