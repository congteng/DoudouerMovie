package net.doudouer.service.recall;

import java.util.List;

import javax.annotation.Resource;

import net.doudouer.dao.impl.DiscussDao;
import net.doudouer.dao.impl.FilmReviewDao;
import net.doudouer.dao.impl.FreshNewsDao;
import net.doudouer.dao.impl.MovieDao;
import net.doudouer.dao.impl.UserDao;
import net.doudouer.dao.impl.UserGibberishDao;
import net.doudouer.domain.Discuss;
import net.doudouer.domain.FilmReview;
import net.doudouer.domain.FreshNews;
import net.doudouer.domain.Movie;
import net.doudouer.domain.User;
import net.doudouer.domain.UserGibberish;

/**
 * 
 * 构造者的简单实现
 * 
 * 通过FreshNews提供的 基本信息 构造 显示在用户 主页上的 完整新鲜事
 * @param newsList
 * @return
 */
public class WebFreshNewsAchiever implements FreshNewsAchiever {

	/**
	 * 注入userGibberishDao
	 */
	@Resource(name = "userGibberishDao")
	private UserGibberishDao userGibberishDao;
	
	/**
	 * 注入movieDao
	 */
	@Resource(name = "movieDao")
	private MovieDao movieDao;
	
	/**
	 * 注入userDao
	 */
	@Resource(name = "userDao")
	private UserDao userDao;
	
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
	 * 注入freshNewsDao
	 */
	@Resource(name = "freshNewsDao")
	private FreshNewsDao freshNewsDao;
	
	public List<FreshNews> generateNewsList(List<FreshNews> newsList) {
		
		for(FreshNews news : newsList){
			String entity = news.getNewsOfEntity();
			// String type = news.getNewsType();
			Long newsID = news.getNewsID();
			String hql = "from " + entity + " e where e.id = " + newsID;
			
			Long who = news.getHappenTo();
			// User whoDid = userDao.getEntity(who);
			
			
			String content = "";
			
			
			if("UserGibberish".equals(entity)){
				// 用户写心情情况
				UserGibberish userGibberish = (UserGibberish) userGibberishDao.uniqueResult(hql);
				content += userGibberish.getContent();
				
				// 如果该心情有评论则 查出所有有关评论
				List<Discuss> userGibberDissList = discussDao.findEntityByHQL("from Discuss d where d.indexOfFreshNews = ?", news.getId());
				news.setDiscussList(userGibberDissList);
			}else if("FilmReview".equals(entity)){
				// 写影评情况
				FilmReview filmReview = (FilmReview) filmReviewDao.uniqueResult(hql);
				Long movieID = filmReview.getMovieID();
				String movieName = (String) movieDao.uniqueResult("select movieName from Movie where id = ?", movieID);
				
				content += "为电影" + movieName + "写了影评:" + 
				(filmReview.getContent().length() > 30 ? filmReview.getContent().substring(0, 30) + "..." : filmReview.getContent());
				
				// 查出影评的评论
				List<Discuss> filmReviewDissList = discussDao.findEntityByHQL("from Discuss d where d.indexOfFreshNews = ?", news.getId());
				news.setDiscussList(filmReviewDissList);
			}else if("Movie".equals(entity)){
				// 收藏电影情况
				Movie movie = (Movie) movieDao.uniqueResult(hql);
				content += movie.getMovieName() + "(" + movie.getReleaseYear() + ")";
				
			}else if("User".equals(entity)){
				// 加好友情况
				User user = (User) userDao.uniqueResult(hql);
				
				String hql_ = "select u.nickName from User u where u.id = ?";
				content = (String) userDao.uniqueResult(hql_, newsID);
			}
			news.setContent(content);
		}
		
		return newsList;
	}

}
