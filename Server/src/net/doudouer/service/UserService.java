package net.doudouer.service;

import java.util.List;

import javax.jws.WebService;

import net.doudouer.domain.Discuss;
import net.doudouer.domain.User;
import net.doudouer.domain.UserGibberish;

public interface UserService extends BaseService<User>{
	
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
	 * 获取推荐的用户
	 * @param userId
	 * @param howmany
	 * @return
	 */
	List<User> getRecommendUsers(Long userId, int howmany);
	
	/**
	 * 根据用户id 得到用户详细信息
	 * @param id
	 * @return
	 */
	String getUserAvatarPathById(Long id);
	
	/**
	 * 根据用户id 得到用户详细信息
	 * @param id
	 * @return
	 */
	String getUserNickNameById(Long id);
	
}
