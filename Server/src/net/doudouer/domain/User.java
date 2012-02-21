package net.doudouer.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import net.doudouer.util.StringUtil;

public class User implements Serializable{

	private static final long serialVersionUID = 5378001079989767351L;
	
	private Long id;
	private String email;
	private String nickName;
	private String password;
	private Long timestamp;
	
	// 用户的 “新鲜事” 内容类型
	private String[] newsTypeArray = new String[]{"addFriend","recordGibberish","writeFilmReview","collectFilm","discussReview"};
	
	/** 我的电影收藏 **/
	private Set<Movie> movieCollection = new HashSet<Movie>();
	
	// 头像地址
	private String avatarLink;
	
	public Set<Movie> getMovieCollection() {
		return movieCollection;
	}

	public void setMovieCollection(Set<Movie> movieCollection) {
		this.movieCollection = movieCollection;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getNewsTypes() {
		if(newsTypeArray != null){
			return StringUtil.connectString(newsTypeArray, "|");
		}else {
			return "";
		}
	}

	public void setNewsTypes(String newsTypes) {
		this.newsTypeArray = newsTypes.split("\\|");
	}

	public String[] getNewsTypeArray() {
		return newsTypeArray;
	}

	public void setNewsTypeArray(String[] newsTypeArray) {
		this.newsTypeArray = newsTypeArray;
	}

	public String getAvatarLink() {
		return avatarLink;
	}

	public void setAvatarLink(String avatarLink) {
		this.avatarLink = avatarLink;
	}

}
