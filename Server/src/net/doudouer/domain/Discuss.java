package net.doudouer.domain;

import java.io.Serializable;

public class Discuss implements Serializable{

	private static final long serialVersionUID = -3067189468978471814L;
	private Long id;
	private Long whoDiss;
	
	// 对哪个实体的评论 现有UserGibberish | FilmReview
	private String dissTo;
	private String content;
	private Long timestamp;
	
	/* 是新鲜事中第几条 */
	private Long indexOfFreshNews;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getWhoDiss() {
		return whoDiss;
	}
	public void setWhoDiss(Long whoDiss) {
		this.whoDiss = whoDiss;
	}
	public String getDissTo() {
		return dissTo;
	}
	public void setDissTo(String dissTo) {
		this.dissTo = dissTo;
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
	public Long getIndexOfFreshNews() {
		return indexOfFreshNews;
	}
	public void setIndexOfFreshNews(Long indexOfFreshNews) {
		this.indexOfFreshNews = indexOfFreshNews;
	}
}
