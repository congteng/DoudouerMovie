package net.doudouer.service;

import java.util.List;

import net.doudouer.domain.Movie;

public interface MovieService extends BaseService<Movie>{
	
	
	/**
	 * 初始化几部电影
	 */
	@Deprecated
	void initMovieData();
	
	/**
	 * 通过userID 获取给该用户评价的电影
	 * 不能是用户收藏的 或者 已经打过分的电影
	 * 做成分页效果
	 * @param userId
	 * @param from
	 * @param len 页长度
	 * @return
	 */
	List<Movie> getEvaluateMovies(Long userID, int from, int len);
	
	/**
	 * 向电影库里增加电影
	 * @param movie
	 */
	void addMovieToBase(Movie movie);
	
	/**
	 * 添加用户的评分
	 * @param userID
	 * @param movieID
	 * @param score
	 */
	void addMoviePreference(Long userID, Long movieID, double score);
	
	/**
	 * 获取推荐给指定用户的电影
	 * @param userID
	 */
	List<Movie> getRecommendMovieList(Long userID, int howmany);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	Movie getMovieById(Long id);
}
