package net.doudouer.test.data;

import java.util.List;
import java.util.Set;

import net.doudouer.domain.Movie;
import net.doudouer.domain.User;
import net.doudouer.movie.recommender.SimpleMovieRecommender;
import net.doudouer.movie.recommender.UserRecommender;
import net.doudouer.service.MovieService;
import net.doudouer.service.UserService;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class DataMaker {

	public static void main(String[] args) {
//		insertAUser();
//		readUser();
//		insertAUser();
//		initMovies();
//		testGetAllMovies();
//		testGetEvaluateMovies();
//		prepareRelationShip();
//		testMovieDataRead();
		
//		testRecommend();
//		testRecommendUsers();
		
//		testCollectMovie();
	}
	
	private static void testCollectMovie() {
		ApplicationContext app = new ClassPathXmlApplicationContext("beans.xml");
		UserService userService = (UserService) app.getBean("userService");
		userService.collectMovie(1L, 15L, 4.0);
	}

	public static void readUser(){
		ApplicationContext app = new ClassPathXmlApplicationContext("beans.xml");
		UserService userService = (UserService) app.getBean("userService");
		
		User user = userService.getEntity(7L);
		System.out.println(user.getNewsTypes());
	}
	
	public static void insertAUser(){
		
		String types = "addFriend|recordGibberish|writeFilmReview|collectFilm|discussReview";
		
		ApplicationContext app = new ClassPathXmlApplicationContext("beans.xml");
		
		UserService userService = (UserService) app.getBean("userService");
		
		User me = new User();
		
		me.setEmail("ct@doudouer.com");
		me.setNickName("ct");
		me.setPassword("123");
		me.setNewsTypes(types);
		me.setTimestamp(System.currentTimeMillis());
		
		userService.saveEntity(me);
		User user1 = new User();
		user1.setEmail("1@doudouer.com");
		user1.setNickName("1");
		user1.setPassword("123");
		user1.setNewsTypes(types);
		user1.setTimestamp(System.currentTimeMillis());
		
		User user2 = new User();
		user2.setEmail("2@doudouer.com");
		user2.setNickName("2");
		user2.setPassword("123");
		user2.setNewsTypes(types);
		user2.setTimestamp(System.currentTimeMillis());
		
		User user3 = new User();
		user3.setEmail("3@doudouer.com");
		user3.setNickName("3");
		user3.setPassword("123");
		user3.setNewsTypes(types);
		user3.setTimestamp(System.currentTimeMillis());
		
		User user4 = new User();
		user4.setEmail("4@doudouer.com");
		user4.setNickName("4");
		user4.setPassword("123");
		user4.setNewsTypes(types);
		user4.setTimestamp(System.currentTimeMillis());
		
		userService.saveEntity(user1);
		userService.saveEntity(user2);
		userService.saveEntity(user3);
		userService.saveEntity(user4);
		
		
	}
	
	public static void initMovies(){
		ApplicationContext app = new ClassPathXmlApplicationContext("beans.xml");
		
		MovieService service = (MovieService) app.getBean("movieService");
		service.initMovieData();
	}
	
	public static void testGetAllMovies(){
		ApplicationContext app = new ClassPathXmlApplicationContext("beans.xml");
		
		MovieService service = (MovieService) app.getBean("movieService");
		Movie movie = service.getEntity(4L);
		
		Set<User> users = movie.getWhoCollect();
		System.out.println(movie);
		for(User user : users){
			System.out.println(user.getNickName());
		}
	}
	
	public static void testGetEvaluateMovies(){
		ApplicationContext app = new ClassPathXmlApplicationContext("beans.xml");
		
		MovieService service = (MovieService) app.getBean("movieService");
		List<Movie> evalutateMovies = service.getEvaluateMovies(12L, 0, 4);
		
		for(Movie movie : evalutateMovies){
			System.out.println(movie.getId());
		}
	}
	
	public static void prepareRelationShip(){
		ApplicationContext app = new ClassPathXmlApplicationContext("beans.xml");
		UserService userService = (UserService) app.getBean("userService");
		
		userService.collectMovie(8L, 1L, 4.5);
		
	}
	
	public static void testMovieDataRead(){
		ApplicationContext app = new ClassPathXmlApplicationContext("beans.xml");
		MovieService service = (MovieService) app.getBean("movieService");
		
		Movie movie = service.getEntity(9L);
		
		System.out.println(movie.getActor());
		System.out.println(movie.getDescription());
		System.out.println(movie.getCountry());
		System.out.println(movie.getDirector());
		System.out.println(movie.getLanguage());
		System.out.println(movie.getType());
		System.out.println(movie.getReleaseYear());
		System.out.println(movie.getMovieName());
	}
	
	public static void testRecommend(){
		ApplicationContext app = new ClassPathXmlApplicationContext("beans.xml");
		
		try {
			SimpleMovieRecommender recommender = (SimpleMovieRecommender) app.getBean("simpleRecommender");
			
			List<RecommendedItem> items = recommender.recommend(1L, 1);
			System.out.println("推荐数据: ");
			for(RecommendedItem item : items){
				System.out.println(item.getItemID());
			}
			
		} catch (TasteException e) {
			e.printStackTrace();
		}
	}
	
	public static void testRecommendUsers(){
		ApplicationContext app = new ClassPathXmlApplicationContext("beans.xml");
		UserRecommender recommender = (UserRecommender) app.getBean("userRecommender");
		long[] ids = recommender.getSimilarUserIdList(1L, 3);
		for(long id : ids){
			System.out.println(id);
		}
	}
	
}
