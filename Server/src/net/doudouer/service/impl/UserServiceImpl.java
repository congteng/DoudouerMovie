package net.doudouer.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.doudouer.dao.BaseDao;
import net.doudouer.dao.impl.DiscussDao;
import net.doudouer.dao.impl.FilmReviewDao;
import net.doudouer.dao.impl.FriendRelationshipDao;
import net.doudouer.dao.impl.MovieDao;
import net.doudouer.dao.impl.MoviePreferenceDao;
import net.doudouer.dao.impl.UserGibberishDao;
import net.doudouer.domain.Discuss;
import net.doudouer.domain.FilmReview;
import net.doudouer.domain.FreshNews;
import net.doudouer.domain.Movie;
import net.doudouer.domain.MoviePreference;
import net.doudouer.domain.User;
import net.doudouer.domain.UserGibberish;
import net.doudouer.movie.recommender.UserRecommender;
import net.doudouer.service.UserService;
import net.doudouer.service.recall.FreshNewsBuilder;
import net.doudouer.util.RandomUtil;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	@Resource(name = "userDao")
	public void setDao(BaseDao<User> dao) {
		super.setDao(dao);
	}

	/**
	 * 注入movieDao
	 */
	@Resource(name = "movieDao")
	private MovieDao movieDao;
	
	/**
	 * 注入moviePreferenceDao
	 */
	@Resource(name = "moviePreferenceDao")
	private MoviePreferenceDao moviePreferenceDao;
	
	/**
	 * 注入userGibberishDao
	 */
	@Resource(name = "userGibberishDao")
	private UserGibberishDao userGibberishDao;

	/**
	 * 注入friendRelationshipDao
	 */
	@Resource(name = "friendRelationshipDao")
	private FriendRelationshipDao friendRelationshipDao;
	
	/**
	 * 注入filmReviewDao
	 */
	@Resource(name = "filmReviewDao")
	private FilmReviewDao filmReviewDao;
	
	/**
	 * 注入discussDao
	 */
	@Resource(name = "discussDao")
	private DiscussDao discussDao;
	
	/**
	 * 注入用户推荐
	 */
	@Resource(name = "userRecommender")
	private UserRecommender userRecommender;

	
	/**
	 * 用于webservice调用的验证用户是否存在的方法
	 * @param email
	 * @param password
	 * @return
	 * 如果返回值不是null则存在该用户
	 */
	public User userLogin4WebService(String email, String password){
		
		User user = (User) uniqueResult("from User u where u.email = ? and u.password = ?", email, password);
		if(user != null){
			user.setMovieCollection(null);
		}
		return user;
	}
	
	/**
	 * 通过具体数值i 随即获取已有用户
	 */
	public List<User> getRandUsersByCounts(int i) {
		List<Object> ids = findObjectByHQL("select id from User");
		List<User> users = null;
		if (ids.size() > i) {
			Long[] needIds = (Long[]) RandomUtil.getRandomIdsFromAllIdsByCount(ids, i);
			users = getUsersByIds(needIds);
		} else {
			users = findEntityByHQL("from User");
		}
		return users;
	}

	/**
	 * 通过ids 查找用户列表
	 */
	public List<User> getUsersByIds(Long... ids) {
		List<User> retVal = new ArrayList<User>();
		for (Long id : ids) {
			User p = getEntity(id);
			retVal.add(p);
		}
		return retVal;
	}

	/**
	 * 某个用户收藏某一电影
	 * 
	 * 如果用户没有打分 , 系统默认将score 设置为 5.0
	 */
	public void collectMovie(Long movieID, Long userID, double score) {
		
		final Movie movie = movieDao.loadEntity(movieID);
		User user = loadEntity(userID);
		
		if(!user.getMovieCollection().contains(movie)){
			// 设置双向关联
			movie.getWhoCollect().add(user);
			user.getMovieCollection().add(movie);	
		
		
			// 设置用户为该电影的打分
			// 如果以前打分过则更新分数
			MoviePreference preference = new MoviePreference();
			preference.setMovieID(movieID);
			preference.setUserID(userID);
			preference.setTimestamp(System.currentTimeMillis());
			preference.setPreference(score);
			
			moviePreferenceDao.saveOrUpdateEntity(preference);
			
			freshNewsService.addWhatFreshNewsToWhoseFriendsAndSelf(userID, "collectFilm", new FreshNewsBuilder(){
	
				public FreshNews buildNews(Long messageReciver, Long happenTo) {
					FreshNews news = new FreshNews();
					news.setUserID(messageReciver);
					news.setHappenTo(happenTo);
					news.setNewsType("collectFilm");
					news.setNewsOfEntity("Movie");
					news.setNewsID(movie.getId());
					news.setTimestamp(System.currentTimeMillis());
					return news;
				}
			});
		}
	}

	/**
	 * 用户发表一个新的 心情
	 * @param userID
	 * @param content
	 */
	public void addANewGibberish(Long userID, String content) {
		
		
		// 插入新的心情
		final UserGibberish userGibberish = new UserGibberish();
		
		userGibberish.setUserID(userID);
		userGibberish.setContent(content);
		userGibberish.setTimestamp(System.currentTimeMillis());
		
		userGibberishDao.saveEntity(userGibberish);
		
		freshNewsService.addWhatFreshNewsToWhoseFriendsAndSelf(userID, "recordGibberish", new FreshNewsBuilder(){

			public FreshNews buildNews(Long userID, Long happenTo) {
				
				FreshNews news = new FreshNews();
				news.setUserID(userID);
				news.setHappenTo(happenTo);
				news.setNewsType("recordGibberish");
				news.setNewsOfEntity("UserGibberish");
				news.setNewsID(userGibberish.getId());
				news.setTimestamp(System.currentTimeMillis());
				return news;
				
			}
		});
	}

	/**
	 * 用户删除一个 心情
	 * @param userID
	 * @param content
	 */
	public void deleteGibberish(Long gibberishID) {
		UserGibberish userGibberish = userGibberishDao.loadEntity(gibberishID);
		if(userGibberish != null){
			userGibberishDao.deleteEntity(userGibberish);
		}
	}

	/**
	 * 查询关于某一个用户的所有的心情
	 * 
	 * desc通常使用 true 因为信息系统显示按照时间倒序
	 * @return
	 */
	public List<UserGibberish> findAllGibberish4OneUser(Long userID, boolean desc) {
		
		String hql = "from UserGibberish u where u.userID = ? order by u.timestamp " + (desc ? "desc" : "asc");
		
		List<UserGibberish> list = userGibberishDao.findEntityByHQL(hql, userID);
		
		return list;
	}

	/**
	 * 写影评
	 * @param userID
	 * @param content
	 */
	public void writeFilmReview(Long userID, String content, Long movieID){
		
		final FilmReview review = new FilmReview();
		review.setContent(content);
		review.setUserID(userID);
		review.setMovieID(movieID);
		
		filmReviewDao.saveEntity(review);
		
		freshNewsService.addWhatFreshNewsToWhoseFriendsAndSelf(userID, "writeFilmReview", new FreshNewsBuilder(){

			public FreshNews buildNews(Long messageReciver, Long happenTo) {
				FreshNews news = new FreshNews();
				news.setUserID(messageReciver);
				news.setHappenTo(happenTo);
				news.setNewsOfEntity("FilmReview");
				news.setNewsID(review.getId());
				news.setNewsType("writeFilmReview");
				news.setTimestamp(System.currentTimeMillis());
				
				return news;
			}
			
		});
	}
	
	/**
	 * 评论其他用户心情
	 * 调用时不需要设置DissTo
	 */
	public void discussToUserGibberish(Long whoDiss, String content, Long index){
		
		List<Object> indexes = freshNewsService.findObjectByHQL("select n.id from FreshNews n where n.newsID = (" +
				"select f.newsID from FreshNews f where f.id = ?)", index);
		
		for(Object in : indexes){
			Discuss discuss = new Discuss();
			discuss.setDissTo("UserGibberish");
			
			discuss.setContent(content);
			discuss.setIndexOfFreshNews((Long)in);
			discuss.setWhoDiss(whoDiss);
			discuss.setTimestamp(System.currentTimeMillis());;
			discussDao.saveEntity(discuss);
		}
	}
	
	
	/**
	 * 评论用户的影评
	 * 调用时不需要设置DissTo
	 * @param discuss
	 */
	public void discussToUserFilmReview(Long whoDiss, String content, Long index){
		
		List<Object> indexes = freshNewsService.findObjectByHQL("select n.id from FreshNews n where n.newsID = (" +
				"select f.newsID from FreshNews f where f.id = ?)", index);
		
		
		
		for(Object in : indexes){
			Discuss discuss = new Discuss();
			discuss.setDissTo("FilmReview");
			
			discuss.setContent(content);
			discuss.setIndexOfFreshNews((Long)in);
			discuss.setWhoDiss(whoDiss);
			discuss.setTimestamp(System.currentTimeMillis());
			discussDao.saveEntity(discuss);
		}
	}
	/**
	 * 保存一个用户
	 * 配置一些默认信息
	 * @param user
	 */
	public void saveUser(User user) {
		String types = "addFriend|recordGibberish|writeFilmReview|collectFilm|discussReview";
		user.setTimestamp(System.currentTimeMillis());
		user.setNewsTypes(types);
		user.setAvatarLink("upload/userAvatar/avatar_default.png");
		saveEntity(user);
	}
	
	/**
	 * 获取推荐的用户
	 * @param userId
	 * @param howmany
	 * @return
	 */
	public List<User> getRecommendUsers(Long userId, int howmany){
		
		long[] userIds = null;
		userIds = userRecommender.getSimilarUserIdList(userId, howmany);
		List<User> retList = new ArrayList<User>();
		
		List<Object> userFriendsId = friendRelationshipDao.findObjectByHQL("select f.friendID from FriendRelationship f where f.userID = ?", userId);
		
		for(long id : userIds){
			// 如果用户好友列表中不存在该用户
			if(!userFriendsId.contains(id)){
				User user = getEntity(id);
				// 初始化集合
				Hibernate.initialize(user.getMovieCollection());
				
				retList.add(user);
			}
		}
		
		return retList;		
		
	}
	
	/**
	 * 根据用户id 得到用户
	 * @param id
	 * @return
	 */
	public String getUserAvatarPathById(Long id){
		return (String) uniqueResult("select u.avatarLink from User u where u.id = ?", id);
	}
	
	/**
	 * 根据用户id 得到用户
	 * @param id
	 * @return
	 */
	public String getUserNickNameById(Long id){
		return (String) uniqueResult("select u.nickName from User u where u.id = ?", id);
	}
}
