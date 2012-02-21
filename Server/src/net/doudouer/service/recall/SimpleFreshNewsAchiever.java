package net.doudouer.service.recall;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.doudouer.dao.impl.FilmReviewDao;
import net.doudouer.dao.impl.FreshNewsDao;
import net.doudouer.dao.impl.MovieDao;
import net.doudouer.dao.impl.UserDao;
import net.doudouer.dao.impl.UserGibberishDao;
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
public class SimpleFreshNewsAchiever implements FreshNewsAchiever {

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
	 * 注入freshNewsDao
	 */
	@Resource(name = "freshNewsDao")
	private FreshNewsDao freshNewsDao;
	
	public List<FreshNews> generateNewsList(List<FreshNews> newsList) {
		
		for(FreshNews news : newsList){
			String entity = news.getNewsOfEntity();
			String type = news.getNewsType();
			Long newsID = news.getNewsID();
			// 类似 from UserGibberish e where e.id = 8 的形式
			String hql = "from " + entity + " e where e.id = " + newsID;
			
			Long who = news.getHappenTo();
			User whoDid = userDao.getEntity(who);
			
			
			Long time = news.getTimestamp();
			String dateTime = DateFormat.getDateTimeInstance().format(new Date(time));
			String content = dateTime + " : \r\t";
			
			
			if("UserGibberish".equals(entity)){
				// 用户写心情情况
				UserGibberish userGibberish = (UserGibberish) userGibberishDao.uniqueResult(hql);
				content += whoDid.getNickName() + " 刚发表心情: " + userGibberish.getContent();
				
			}else if("FilmReview".equals(entity)){
				// 写影评情况
				FilmReview filmReview = (FilmReview) filmReviewDao.uniqueResult(hql);
				Long movieID = filmReview.getMovieID();
				String movieName = (String) movieDao.uniqueResult("select movieName from Movie where id = ?", movieID);
				
				content += whoDid.getNickName() + " 刚给" + movieName + "电影写了影评: "
				+ (filmReview.getContent().length() > 30 ? filmReview.getContent().substring(0, 30) : filmReview.getContent()) 
				+ "...";
				
			}else if("Movie".equals(entity)){
				// 收藏电影情况
				Movie movie = (Movie) movieDao.uniqueResult(hql);
				content += whoDid.getNickName() + " 刚收藏了电影: " + movie.getMovieName() + "(" + movie.getReleaseYear() + ")";
				
			}else if("User".equals(entity)){
				// 加好友情况
				User user = (User) userDao.uniqueResult(hql);
				content += whoDid.getNickName() + " 刚加了好友: " + user.getNickName();
			}
			news.setContent(content);
		}
		
		return newsList;
	}

}
