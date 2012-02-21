package net.doudouer.webservice;

import java.util.List;

import javax.jws.WebService;

import net.doudouer.domain.Discuss;
import net.doudouer.domain.FreshNews;
import net.doudouer.domain.Movie;
import net.doudouer.domain.User;
import net.doudouer.domain.UserGibberish;
import net.doudouer.service.recall.FreshNewsBuilder;

@WebService
public interface DoudouerWebService {
	
	/**
	 * 用于webservice调用的验证用户是否存在的方法
	 * @param email
	 * @param password
	 * @return
	 * 如果返回值不是null则存在该用户
	 */
	User userLogin4WebService(String email, String password);
	
	/**
	 * 某用户收藏某电影
	 * 
	 * 前提要为该电影打分才可收藏
	 * 
	 * @param movieId
	 * @param userId
	 * @param score
	 */
	void collectMovie(Long movieID, Long userId, double score);
	
	/**
	 * 用户发表一个新的 心情
	 * @param userID
	 * @param content
	 */
	void addANewGibberish(Long userID, String content);
	
	/**
	 * 用户删除一个 心情
	 * @param userID
	 * @param content
	 */
	void deleteGibberish(Long gibberishID);
	
	/**
	 * 查询关于某一个用户的所有的心情
	 * @return
	 */
	List<UserGibberish> findAllGibberish4OneUser(Long userID, boolean desc);
	
	/**
	 * 保存一个用户
	 * 配置一些默认信息
	 * @param user
	 */
	void saveUser(User user);
	
	/**
	 * 写影评
	 * @param userID
	 * @param content
	 */
	void writeFilmReview(Long userID, String content, Long movieID);
	/**
	 * 评论其他用户心情
	 */
	void discussToUserGibberish(Long whoDiss, String content, Long index);
	
	/**
	 * 评论用户的影评
	 * @param discuss
	 */
	public void discussToUserFilmReview(Long whoDiss, String content, Long index);
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
	 * 增加1-n个好友
	 * @param userID
	 * @param ids
	 */
	void addNewFriends(Long userID, List<Long> ids);
	
	/**
	 * 删除 1-n个好友
	 * @param userID
	 * @param ids
	 */
	void deleteSomeFriends(Long userID, List<Long> ids);

	/**
	 * 获取用户所有的好友
	 * @param userID
	 * @return
	 */
	List<User> getAllFriends(Long userID);

	/**
	 * 获取该用户的新鲜事列表
	 * @param userID 用户id
	 * @param off 从第几个数据开始
	 * @param len 每页的长度
	 * @param desc 是否倒序
	 * @return 返回新鲜事对象
	 */
	List<FreshNews> getFreshNewsForSpecificUser(Long userID, int off, int len, boolean desc);
	
	/**
	 * 根据用户id 得到用户
	 * @param id
	 * @return
	 */
	String getUserAvatarPathById(Long id);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	Movie getMovieById(Long id);
	
	
	/**
	 * 根据用户id 得到用户详细信息
	 * @param id
	 * @return
	 */
	String getUserNickNameById(Long id);
}
