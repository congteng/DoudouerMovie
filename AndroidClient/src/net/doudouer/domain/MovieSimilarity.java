package net.doudouer.domain;

import java.io.Serializable;

public class MovieSimilarity implements Serializable{

	private static final long serialVersionUID = 1458254627179745606L;
	
	private Long movieID1;
	private Long movieID2;
	private double similarity;
	
	public Long getMovieID1() {
		return movieID1;
	}
	public void setMovieID1(Long movieID1) {
		this.movieID1 = movieID1;
	}
	public Long getMovieID2() {
		return movieID2;
	}
	public void setMovieID2(Long movieID2) {
		this.movieID2 = movieID2;
	}
	public double getSimilarity() {
		return similarity;
	}
	public void setSimilarity(double similarity) {
		this.similarity = similarity;
	}
	
}
