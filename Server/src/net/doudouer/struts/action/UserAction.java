package net.doudouer.struts.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.resource.spi.DissociatableManagedConnection;

import net.doudouer.domain.Discuss;
import net.doudouer.domain.FreshNews;
import net.doudouer.domain.Movie;
import net.doudouer.domain.MoviePreference;
import net.doudouer.domain.User;
import net.doudouer.movie.recommender.MovieRecommender;
import net.doudouer.movie.recommender.SimpleMovieRecommender;
import net.doudouer.service.FreshNewsService;
import net.doudouer.service.FriendRelationshipService;
import net.doudouer.service.MovieService;
import net.doudouer.service.UserService;

import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.views.freemarker.tags.SetModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * 提供用户的服务Action
 * @author congteng
 */
@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> implements SessionAware {

	private static final long serialVersionUID = -1079556710847581525L;

	// 模型
	private User model = new User();
	
	@Resource(name = "userService")
	private UserService userService;
	
	@Resource(name = "movieService")
	private MovieService movieService;
	
	@Resource(name = "freshNewsService")
	private FreshNewsService freshNewsService;
	
	@Resource(name = "friendRelationshipService")
	private FriendRelationshipService friendRelationshipService;
	
	/* 给用户的打分电影列表 */
	private List<Movie> evaluateMovieList = new ArrayList<Movie>();
	/* 新鲜事列表 */
	private List<FreshNews> newsList = new ArrayList<FreshNews>();

	/* 推荐电影列表 */
	private List<Movie> recommendMovieList = new ArrayList<Movie>();
	
	/* 推荐好友列表 */
	private List<User> recommendUserList = new ArrayList<User>();
	
	/* 用户的评分 */
	private double score;
	
	private int pageNum = 0;
	
	/* 页长度 */
	private int len = 4;
	/* 被打分的电影 */
	private Long movieID;
	/* 评论类型 */
	private String type;
	/* 对实体中哪条记录评论 */
	private Long userID;
	/* 评论内容 */
	private String dissContent;
	/* 评论的是FreshNews中的第几条记录 */
	private Long indexOfFreshNews;

	private Map<String, Object> sessionMap;
	
	
	private User infoUser;
	private List<Object> userCollection;
	

	public User getInfoUser() {
		return infoUser;
	}

	public void setInfoUser(User infoUser) {
		this.infoUser = infoUser;
	}

	@Override
	public User getModel() {
		return model;
	}
	
	public String getUserInfo(){
		
		String hql = "from User u where u.id=?";
		
		infoUser = (User) userService.uniqueResult(hql, ((User)sessionMap.get("user")).getId());
		
		return "userInfo";
	}
	
	public String getFriends(){
		String hql = "from User u where u.id in(select f.friendID from FriendRelationship f where f.userID=?)";
		
		userCollection = userService.findObjectByHQL(hql, ((User)sessionMap.get("user")).getId());
		return "friendsInfo";
	}
	
	public String getCollection(){
		String hql = "select u.movieCollection from User u where u.id=?";
		
		userCollection = userService.findObjectByHQL(hql, ((User)sessionMap.get("user")).getId());
		
		return "collectionInfo";
	}
	
	public String addANewGibberish(){
		
		userService.addANewGibberish(((User)sessionMap.get("user")).getId(), dissContent);
		
		return null;
	}
	
	/**
	 * 给电影打分
	 * @return
	 */
	public String evaluateMovie(){
		movieService.addMoviePreference(((User)sessionMap.get("user")).getId(), movieID, score);
		
		evaluateMovieList = movieService.getEvaluateMovies(((User)sessionMap.get("user")).getId(), 0, 10);
		return "showMovies";
	}
	
	/**
	 * 给推荐电影打分
	 * @return
	 */
	public String evaRecommendMovie(){
		movieService.addMoviePreference(((User)sessionMap.get("user")).getId(), movieID, score);
		recommendMovieList = movieService.getRecommendMovieList(model.getId(), 2);
		return "showMovies";
	}
	
	/**
	 * 到给电影评分的界面
	 * @return
	 */
	public String toEvaluateMovies(){
		evaluateMovieList = movieService.getEvaluateMovies(((User)sessionMap.get("user")).getId(), 0, 10);
		return "evaluateMoviePage";
	}
	
	/**
	 * 到推荐电影页面 
	 * @return
	 */
	public String toRecommendMoviesPage(){
		recommendMovieList = movieService.getRecommendMovieList(((User)sessionMap.get("user")).getId(), 2);
		return "recommendMoviePage";
	}
	
	/**
	 * 收藏电影
	 * @return
	 */
	public String collectMovie(){
		// 默认收藏了的电影都是5分
		userService.collectMovie(movieID, ((User)sessionMap.get("user")).getId(), 5);
		return null;
	}
	
	/**
	 * 到推荐用户页面
	 * @return
	 */
	public String toRecommendUsersPage(){
		recommendUserList = userService.getRecommendUsers(((User)sessionMap.get("user")).getId(), 2);
		return "recommendUserPage";
	}
	
	/**
	 * 添加好友
	 * @return
	 */
	public String addNewFriend(){
		
		List<Long> fIds = new ArrayList<Long>();
		fIds.add(userID);
		friendRelationshipService.addNewFriends(((User)sessionMap.get("user")).getId(), fIds);
		
		return null;
	}
	
	/**
	 * 查看新鲜事
	 * @return
	 */
	public String toFreshNewsView(){
		newsList = freshNewsService.getFreshNewsForSpecificUser(((User)sessionMap.get("user")).getId(), 0, len, true);
		return "freshNewsListPage";
	}
	
	/**
	 * 获取更多新鲜事
	 * @return
	 */
	public String getMoreFreshNews(){
		int index = pageNum * len;
		newsList = freshNewsService.getFreshNewsForSpecificUser(((User)sessionMap.get("user")).getId(), index, len, true);
		return "showFreshNews";
	}
	
	/**
	 * 
	 * 对新鲜事进行评论
	 * @return
	 */
	public String discussToFreshNews(){
		
		
		if("UserGibberish".equals(type)){
			userService.discussToUserGibberish(((User)sessionMap.get("user")).getId(), dissContent, indexOfFreshNews);
		}else if("FilmReview".equals(type)){
			userService.discussToUserFilmReview(((User)sessionMap.get("user")).getId(), dissContent, indexOfFreshNews);
		}
		return null;
	}
	/**
	 * 推荐用户给登陆的用户
	 * @param happenTo
	 * @return
	 */
	
	/* 供struts2标签调用 */
	public String getAvatarPath(Long happenTo){
		return (String) userService.uniqueResult("select u.avatarLink from User u where u.id = ?", happenTo);
	}
	public String getUserNameFromId(Long happenTo){
		return (String) userService.uniqueResult("select u.nickName from User u where u.id = ?", happenTo);
	}
	public String getMovieNameFromId(Long movieID){
		return (String) movieService.uniqueResult("select m.movieName from Movie m where m.id = ?", movieID);
	}
	
	/////////////////
	public List<Movie> getEvaluateMovieList() {
		return evaluateMovieList;
	}

	public void setEvaluateMovieList(List<Movie> evaluateMovieList) {
		this.evaluateMovieList = evaluateMovieList;
	}
	
	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public Long getMovieID() {
		return movieID;
	}

	public void setMovieID(Long movieID) {
		this.movieID = movieID;
	}

	public FreshNewsService getFreshNewsService() {
		return freshNewsService;
	}

	public void setFreshNewsService(FreshNewsService freshNewsService) {
		this.freshNewsService = freshNewsService;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public List<FreshNews> getNewsList() {
		return newsList;
	}

	public void setNewsList(List<FreshNews> newsList) {
		this.newsList = newsList;
	}

	public int getLen() {
		return len;
	}

	public void setLen(int len) {
		this.len = len;
	}

	public List<Movie> getRecommendMovieList() {
		return recommendMovieList;
	}

	public void setRecommendMovieList(List<Movie> recommendMovieList) {
		this.recommendMovieList = recommendMovieList;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public String getDissContent() {
		return dissContent;
	}

	public void setDissContent(String dissContent) {
		this.dissContent = dissContent;
	}

	public Long getIndexOfFreshNews() {
		return indexOfFreshNews;
	}

	public void setIndexOfFreshNews(Long indexOfFreshNews) {
		this.indexOfFreshNews = indexOfFreshNews;
	}

	public List<User> getRecommendUserList() {
		return recommendUserList;
	}

	public void setRecommendUserList(List<User> recommendUserList) {
		this.recommendUserList = recommendUserList;
	}

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public void setSession(Map<String, Object> arg0) {
		this.sessionMap = arg0;
	}

	public List<Object> getUserCollection() {
		return userCollection;
	}
	
	public void setUserCollection(List<Object> userCollection) {
		this.userCollection = userCollection;
	}
}
