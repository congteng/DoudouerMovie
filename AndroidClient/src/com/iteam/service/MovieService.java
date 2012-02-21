package com.iteam.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import net.doudouer.domain.Movie;
import org.ksoap2.serialization.SoapObject;
import org.xmlpull.v1.XmlPullParserException;
import android.graphics.drawable.BitmapDrawable;
import com.iteam.logic.Task;
import com.iteam.util.NetUtil;

public class MovieService {
	
	//保存电影图片
	public static HashMap<Long,BitmapDrawable> allMoviePoster = new HashMap<Long,BitmapDrawable>();

	/**
	 * 得到设计到的电影图片
	 * @param movies
	 */
	public static void getAllMoviePoster(List<Movie> movies) {
		//获取电影图片
		Set<Long> movieIDs = new HashSet<Long>();//得到所有电影ID
		for (int i = 0; i < movies.size(); i++) {
			long id = movies.get(i).getId();
			if(!movieIDs.contains(id)) {
				BitmapDrawable bd = MovieService.allMoviePoster.get(id); 
				if(bd == null) {
					HashMap<Object, Object> param2 = new HashMap<Object, Object>();
					param2.put("id",id);
					param2.put("path", movies.get(i).getAvatarLink());
					Task task2 = new Task(Task.GET_MOVIE_POSTER,param2);
					MainService.newTask(task2);
				}
			};
			movieIDs.add(id);//此用户头像已经添加，记录
		}
	}
	
	/**
	 * 通过userID 获取给该用户评价的电影
	 * 不能是用户收藏的 或者 已经打过分的电影
	 * @param userID
	 * @param from
	 * @param len
	 * @return
	 * @throws IOException
	 * @throws XmlPullParserException
	 */
	@SuppressWarnings("unchecked")
	public static List<Movie> getEvaluateMovies(Long userID, int from, int len) throws IOException, XmlPullParserException{
		String METHOD_NAME = "getEvaluateMovies";
		SoapObject soapObject = new SoapObject(MainService.NAMESPACE, METHOD_NAME);
		soapObject.addProperty("arg0", userID);
		soapObject.addProperty("arg1", from);
		soapObject.addProperty("arg2", len);
		//生成调用webService方法的SOAP请求信息。
		Vector<Object> resultVector = (Vector<Object>) NetUtil.useEnvelope(soapObject);;
		return parseMovies(resultVector);
	}
	/**
	 * 获取推荐给指定用户的电影
	 * @param userID
	 * @param from
	 * @param len
	 * @return
	 * @throws IOException
	 * @throws XmlPullParserException
	 */
	@SuppressWarnings("unchecked")
	public static List<Movie> getRecommendMovieList(Long userID, int howmany) throws IOException, XmlPullParserException{
		String METHOD_NAME = "getRecommendMovieList";
		SoapObject soapObject = new SoapObject(MainService.NAMESPACE, METHOD_NAME);
		soapObject.addProperty("arg0", userID);
		soapObject.addProperty("arg1", howmany);
		//生成调用webService方法的SOAP请求信息。
		try {
			Vector<Object> resultVector = (Vector<Object>) NetUtil.useEnvelope(soapObject);;
			return parseMovies(resultVector);
		} catch (ClassCastException e) {
			SoapObject result = (SoapObject)NetUtil.useEnvelope(soapObject);
			Movie movie = parseMovie(result);
			List<Movie> moviesList = new ArrayList<Movie>();
			moviesList.add(movie);
			return moviesList;
		}
	}
	
	public static Movie getMovieById (long id) throws IOException, XmlPullParserException {
		String METHOD_NAME = "getMovieById";
		SoapObject soapObject = new SoapObject(MainService.NAMESPACE, METHOD_NAME);
		soapObject.addProperty("arg0", id);
		//生成调用webService方法的SOAP请求信息。
		SoapObject result = (SoapObject)NetUtil.useEnvelope(soapObject);
		return parseMovie(result);
	}
	
	public static Movie parseMovie(SoapObject result) {
		Long id = Long.valueOf(result.getProperty("id").toString());
		String movieName = result.getProperty("movieName").toString();
		String type = result.getProperty("type").toString();
		String director = result.getProperty("director").toString();
		String actor = result.getProperty("actor").toString();
		String description = result.getProperty("description").toString();
		String country = result.getProperty("country").toString();
		String language = result.getProperty("language").toString();
		String releaseYear = result.getProperty("releaseYear").toString();
		String avatarLink = result.getProperty("avatarLink").toString();
	  
		Movie movie = new Movie();
		movie.setActor(actor);
		movie.setId(id);
		movie.setMovieName(movieName);
		movie.setType(type);
		movie.setDescription(description);
		movie.setDirector(director);
		movie.setCountry(country);
		movie.setLanguage(language);
		movie.setReleaseYear(releaseYear);
		movie.setAvatarLink(avatarLink);
		
		return movie;
	}
	
	public static List<Movie> parseMovies(Vector<Object> resultVector) {
		List<Movie> moviesList = new ArrayList<Movie>();
		for(int i = 0; i < resultVector.size(); i++){
	       SoapObject detail = (SoapObject) resultVector.elementAt(i);
	       moviesList.add(parseMovie(detail));
		}
		return moviesList;
	}
}
