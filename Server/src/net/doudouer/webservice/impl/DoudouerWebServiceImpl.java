package net.doudouer.webservice.impl;

import java.util.List;

import javax.jws.WebService;

import net.doudouer.dao.impl.FriendRelationshipDao;
import net.doudouer.domain.Discuss;
import net.doudouer.domain.FreshNews;
import net.doudouer.domain.Movie;
import net.doudouer.domain.User;
import net.doudouer.domain.UserGibberish;
import net.doudouer.service.FreshNewsService;
import net.doudouer.service.FriendRelationshipService;
import net.doudouer.service.MovieService;
import net.doudouer.service.MovieSimilarityService;
import net.doudouer.service.UserService;
import net.doudouer.service.recall.FreshNewsBuilder;
import net.doudouer.webservice.DoudouerWebService;

@WebService
public class DoudouerWebServiceImpl implements DoudouerWebService {

	private UserService userService;
	private FreshNewsService freshNewsService;
	private FriendRelationshipService friendRelationshipService;
	private MovieService movieService;
	private MovieSimilarityService movieSimilarityService;
	
	public FreshNewsService getFreshNewsService() {
		return freshNewsService;
	}

	public void setFreshNewsService(FreshNewsService freshNewsService) {
		this.freshNewsService = freshNewsService;
	}

	public FriendRelationshipService getFriendRelationshipService() {
		return friendRelationshipService;
	}

	public void setFriendRelationshipService(FriendRelationshipService friendRelationshipService) {
		this.friendRelationshipService = friendRelationshipService;
	}

	public MovieService getMovieService() {
		return movieService;
	}

	public void setMovieService(MovieService movieService) {
		this.movieService = movieService;
	}

	public MovieSimilarityService getMovieSimilarityService() {
		return movieSimilarityService;
	}

	public void setMovieSimilarityService(MovieSimilarityService movieSimilarityService) {
		this.movieSimilarityService = movieSimilarityService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public List<UserGibberish> findAllGibberish4OneUser(Long userID, boolean desc) {
		return userService.findAllGibberish4OneUser(userID, desc);
	}

	public void addANewGibberish(Long userID, String content) {
		userService.addANewGibberish(userID, content);
	}

	public void addMoviePreference(Long userID, Long movieID, double score) {
		movieService.addMoviePreference(userID, movieID, score);
	}

	public void addMovieToBase(Movie movie) {
		movieService.addMovieToBase(movie);
	}

	public void addNewFriends(Long userID, List<Long> ids) {
		friendRelationshipService.addNewFriends(userID, ids);
	}

	public void collectMovie(Long movieID, Long userId, double score) {
		userService.collectMovie(movieID, userId, score);
	}

	public void deleteGibberish(Long gibberishID) {
		userService.deleteGibberish(gibberishID);
	}

	public void deleteSomeFriends(Long userID, List<Long> ids) {
		friendRelationshipService.deleteSomeFriends(userID, ids);
	}

	public void discussToUserGibberish(Long whoDiss, String content, Long index){
		userService.discussToUserGibberish(whoDiss, content, index);
	}
	
	public void discussToUserFilmReview(Long whoDiss, String content, Long index){
		userService.discussToUserFilmReview(whoDiss, content, index);
		
	}
	public List<User> getAllFriends(Long userID) {
		return friendRelationshipService.getAllFriends(userID);
	}

	public List<Movie> getEvaluateMovies(Long userID, int from, int len) {
		return movieService.getEvaluateMovies(userID, from, len);
	}

	public List<FreshNews> getFreshNewsForSpecificUser(Long userID, int off, int len, boolean desc) {
		return freshNewsService.getFreshNewsForSpecificUser(userID, off, len, desc);
	}

	public List<Movie> getRecommendMovieList(Long userID, int howmany) {
		return movieService.getRecommendMovieList(userID, howmany);
	}

	public void saveUser(User user) {
		userService.saveUser(user);
	}

	public void writeFilmReview(Long userID, String content, Long movieID) {
		userService.writeFilmReview(userID, content, movieID);
	}

	public User userLogin4WebService(String email, String password) {
		return userService.userLogin4WebService(email, password);
	}

	public Movie getMovieById(Long id) {
		return movieService.getMovieById(id);
	}

	public String getUserAvatarPathById(Long id) {
		return userService.getUserAvatarPathById(id);
	}
	/**
	 * 根据用户id 得到用户详细信息
	 * @param id
	 * @return
	 */
	public String getUserNickNameById(Long id){
		return userService.getUserNickNameById(id);
	}

}
