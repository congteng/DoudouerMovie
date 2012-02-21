package net.doudouer.test.data;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.doudouer.domain.Discuss;
import net.doudouer.domain.FreshNews;
import net.doudouer.domain.Movie;
import net.doudouer.domain.User;
import net.doudouer.domain.UserGibberish;
import net.doudouer.service.FreshNewsService;
import net.doudouer.service.FriendRelationshipService;
import net.doudouer.service.MovieService;
import net.doudouer.service.UserService;
import net.doudouer.service.recall.FreshNewsAchiever;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestServiceMethods {

	public static ApplicationContext app = new ClassPathXmlApplicationContext("beans.xml");

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
//		testGetMovieById();		
//		 testAddFriends();
		// testuserLogin4WebService();
		// testQueryFriends();
		// testDeleteFriends();

//		testAddG();
		// testQueryG();
		// testDeleteG();

		// testTimeStamp();

		 testWriteFilmReview();
		// testDiscuss();

		// testGetFreshNews();

		// testFreshNewsAchiever();

		// testFinalFreshNewsGetter();
		
//		testGetMovieById();
//		testGetAvatarPath();
	}

	public static void testuserLogin4WebService() {
		UserService us = (UserService) app.getBean("userService");
		User user = us.userLogin4WebService("111", "111");
		System.out.println("user:" + user);

	}

	public static void testFinalFreshNewsGetter() {
		FreshNewsService fns = (FreshNewsService) app.getBean("freshNewsService");

		List<FreshNews> newsList = fns.getFreshNewsForSpecificUser(14L, 0, 10, true);

		for (FreshNews news : newsList) {
			System.out.println(news.getContent());
		}
	}

	public static void testFreshNewsAchiever() {

		FreshNewsAchiever achiever = (FreshNewsAchiever) app.getBean("freshNewsAchiever");

		achiever.generateNewsList(null);
	}

	public static void testGetFreshNews() {
		FreshNewsService fns = (FreshNewsService) app.getBean("freshNewsService");
		List<FreshNews> newsList = fns.getFreshNewsForSpecificUser(14L, 2, 2, true);
		for (FreshNews news : newsList) {
			Long time = news.getTimestamp();
			String dateTime = DateFormat.getDateTimeInstance().format(new Date(time));

			System.out.println("时间：" + dateTime);

			Long who = news.getHappenTo();
			System.out.println("User : " + who);

			String entity = news.getNewsOfEntity();
			String type = news.getNewsType();
			Long newsID = news.getNewsID();
			String hql = "from " + entity + " e where e.id = " + newsID;
			System.out.println("generate hql : " + hql);
		}
	}

	public static void testDiscuss() {
		UserService us = (UserService) app.getBean("userService");

		/*
		 * Discuss filmDiscuss = new Discuss(); filmDiscuss.setContent("评论的啥啊");
		 * filmDiscuss.setWhoDiss(13L);
		 * 
		 * Discuss gibberishDiscuss = new Discuss();
		 * gibberishDiscuss.setContent("哦哦哦"); gibberishDiscuss.setWhoDiss(13L);
		 * 
		 * us.discussToUserFilmReview(filmDiscuss);
		 * us.discussToUserGibberish(gibberishDiscuss);
		 */
	}

	public static void testWriteFilmReview() {
		UserService us = (UserService) app.getBean("userService");
		us.writeFilmReview(32L, "这是一部好电影！！！", 2L);
	}

	public static void testAddFriends() {
		FriendRelationshipService fr = (FriendRelationshipService) app.getBean("friendRelationshipService");

		List<Long> ids = new ArrayList<Long>();
		/* ids.add(13L); */
		ids.add(23L);
		ids.add(24L);
		ids.add(25L);
		ids.add(26L);
		ids.add(27L);
		ids.add(28L);
		ids.add(29L);
		ids.add(30L);
		ids.add(32L);

		fr.addNewFriends(31L, ids);
	}

	public static void testQueryFriends() {
		FriendRelationshipService fr = (FriendRelationshipService) app.getBean("friendRelationshipService");

		List<User> frs = fr.getAllFriends(12L);
		for (User user : frs) {
			System.out.println(user.getEmail() + " : " + user.getNickName());
		}

	}

	public static void testDeleteFriends() {
		FriendRelationshipService fr = (FriendRelationshipService) app.getBean("friendRelationshipService");
		List<Long> ids = new ArrayList<Long>();
		ids.add(15L);

		fr.deleteSomeFriends(12L, ids);
	}

	public static void testAddG() {
		UserService us = (UserService) app.getBean("userService");
		us.addANewGibberish(1L, "yuggkjhkj");
		// us.addANewGibberish(15L, "呵呵呵");
	}

	public static void testQueryG() {
		UserService us = (UserService) app.getBean("userService");
		List<UserGibberish> gibbs = us.findAllGibberish4OneUser(12L, true);

		for (UserGibberish g : gibbs) {
			System.out.println(g.getContent());
		}

	}

	public static void testDeleteG() {
		UserService us = (UserService) app.getBean("userService");
		us.deleteGibberish(2L);
	}

	public static void getFriendsG() {
		UserService us = (UserService) app.getBean("userService");
	}

	public static void testTimeStamp() {
		Long date = 1313203007281L;
		Date data = new Date(date);
		System.out.println(DateFormat.getDateTimeInstance().format(date));
	}

	public static void testGetMovieById() {
		MovieService mService = (MovieService) app.getBean("movieService");
		Movie m = mService.getMovieById(2L);
		
		System.out.println(m.getDescription());
	}
	
	public static void testGetAvatarPath(){
		UserService service = (UserService) app.getBean("userService");
		System.out.println(service.getUserAvatarPathById(1L));
	}
	
}
