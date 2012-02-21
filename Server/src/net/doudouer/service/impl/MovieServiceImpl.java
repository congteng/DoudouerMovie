package net.doudouer.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import net.doudouer.dao.BaseDao;
import net.doudouer.dao.impl.MovieDao;
import net.doudouer.dao.impl.MoviePreferenceDao;
import net.doudouer.dao.impl.UserDao;
import net.doudouer.domain.Movie;
import net.doudouer.domain.MoviePreference;
import net.doudouer.domain.User;
import net.doudouer.movie.recommender.SimpleMovieRecommender;
import net.doudouer.service.MovieService;
import net.doudouer.util.MovieImporterUtil;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

@Service("movieService")
public class MovieServiceImpl extends BaseServiceImpl<Movie> implements MovieService {

	@Resource(name = "movieDao")
	public void setDao(BaseDao<Movie> dao) {
		super.setDao(dao);
	}
	
	@Resource(name = "moviePreferenceDao")
	private MoviePreferenceDao moviePreferenceDao; 
	
	@Resource(name = "simpleRecommender")
	private SimpleMovieRecommender movieRecommender; 
	
	@Resource(name = "userDao")
	private UserDao userDao;
	/**
	 * 初始化几部电影
	 */
	
	@Deprecated
	public void initMovieData() {
		List<Movie> movies = MovieImporterUtil.dataConverter();
		for(Movie movie : movies){
			saveEntity(movie);
		}
	}

	/**
	 * 获取打分电影
	 */
	public List<Movie> getEvaluateMovies(Long userID, int off, int len) {
		// 不是用户打过分的电影
		// 查出该用户打过分的电影
		Collection<Object> evaluated = findObjectByHQL("select m.movieID from MoviePreference m where m.userID = ?", userID);
		
		// 不是用户收藏过的电影
		// 这就需要用户收藏电影的时候 必须为电影打分， 并将此数据记录到MoviePreference 表中！!!!
		List<Movie> collectMovies = findEntityByHQL("select movieCollection from User u where u.id = ?", userID);
		
		Set<Object> movieIds = new HashSet<Object>();
		for(Movie movie : collectMovies){
			movieIds.add(movie.getId());
		}
		
		movieIds.addAll(evaluated);
		String hql = "from Movie";
		if(movieIds.size() > 0){
			hql = "from Movie m where m.id not in(";
			for(Object movieId: movieIds){
				hql += movieId + ",";
			}
			hql = hql.substring(0, hql.length()-1);
			
				hql += ")";
		}
		List<Movie> retVal = findPagingByHQL(hql, off, len);
		for(Movie movie : retVal){
			movie.setWhoCollect(null);
		}
		
		return retVal;
	}

	/**
	 * 向电影库里增加电影
	 * @param movie
	 */
	public void addMovieToBase(Movie movie) {
		saveOrUpdateEntity(movie);
	}

	public void addMoviePreference(Long userID, Long movieID, double score) {
		MoviePreference mp = new MoviePreference();
		mp.setMovieID(movieID);
		mp.setUserID(userID);
		mp.setPreference(score);
		mp.setTimestamp(System.currentTimeMillis());
		
		moviePreferenceDao.saveOrUpdateEntity(mp);
	}

	/**
	 * 获取推荐给指定用户的电影
	 * @param userID
	 */
	public List<Movie> getRecommendMovieList(Long userID, int howmany) {

		List<RecommendedItem> rList = null;
		List<Movie> retList = null;
		try {
			rList = movieRecommender.recommend(userID, howmany);
		} catch (TasteException e) {
			// 不处理
		}
		if(rList != null){
			retList = new ArrayList<Movie>();
			
			// 查询用户评价过的电影, 该电影不应该已经被用户评分过
			List<Object> preMovieIds = 
					moviePreferenceDao.findObjectByHQL("select m.movieID from MoviePreference m where m.userID = ?", userID);
			
			for(RecommendedItem item : rList){
				Long mId = item.getItemID();
				
				// 排除评价过的
				if(!preMovieIds.contains(mId)){
					Movie movie = getEntity(mId);
					
					// 用户是否已经收藏过该影片, 排除收藏过的
					if(!movie.getWhoCollect().contains(userID)){
						movie.setWhoCollect(null);
						retList.add(movie);
					}
					
				}
			}
		}
		
		return retList;
				
	}
	
	/**
	 * 根据用户电影id 得到电影详细信息
	 * @param id
	 * @return
	 */
	public Movie getMovieById(Long id){
		Movie m = getEntity(id);
		m.setWhoCollect(null);
		return m;
	}
}
