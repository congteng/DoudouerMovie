package com.iteam.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.Marshal;
import org.ksoap2.serialization.MarshalFloat;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import com.iteam.logic.Task;
import com.iteam.util.NetUtil;

import android.graphics.drawable.BitmapDrawable;
import transport.MySoapEnvelope;
import transport.UserSerializable;
import net.doudouer.domain.FreshNews;
import net.doudouer.domain.User;

public class UserService {
	//保存用户头像
	public static HashMap<Long,BitmapDrawable> alluserIcon = new HashMap<Long,BitmapDrawable>();
	public static BitmapDrawable nowUserIcon = null;
	//保存用户昵称
	public static HashMap<Long,String> alluserNickName = new HashMap<Long,String>();
	
	/**
	 * 用户登陆
	 * @param email
	 * @param password
	 * @return
	 * @throws IOException
	 * @throws XmlPullParserException
	 */
	public static User userLogin(String email, String password) throws IOException, XmlPullParserException{
		String METHOD_NAME = "userLogin4WebService";
		SoapObject soapObject = new SoapObject(MainService.NAMESPACE, METHOD_NAME);
		soapObject.addProperty("arg0", email);
		soapObject.addProperty("arg1", password);
		//生成调用webService方法的SOAP请求信息。
		SoapObject result = (SoapObject)NetUtil.useEnvelope(soapObject);
		return parseUser(result);
	}

	/**
	 * 用户注册
	 * @param email
	 * @param password
	 * @param nickName
	 * @return
	 * @throws IOException
	 * @throws XmlPullParserException
	 */
	public static boolean userRegister(String email, String password, String nickName) throws IOException, XmlPullParserException {
		User user = new User();
		String types = "addFriend|recordGibberish|writeFilmReview|collectFilm|discussReview";
		user.setNewsTypes(types);
		user.setEmail(email);
		user.setPassword(password);
		user.setNickName(nickName);
//		user.setAvatarLink("");
		user.setTimestamp(System.currentTimeMillis());
		String METHOD_NAME = "saveUser";
		SoapObject soapObject = new SoapObject(MainService.NAMESPACE, METHOD_NAME);
		soapObject.addProperty("arg0", user);
		//生成调用webService方法的SOAP请求信息
		MySoapEnvelope envelope = new MySoapEnvelope(SoapEnvelope.VER11);
		//要传递复杂类型，需要将复杂类型通过实现KvmSerializable接口序列化
		envelope.addMapping(MainService.NAMESPACE, "userSerializable", UserSerializable.class);
		envelope.encodingStyle = "utf-8";
		envelope.dotNet = false;
		envelope.setAddAdornments(true);
		envelope.implicitTypes = true;
		envelope.setOutputSoapObject(soapObject);
		HttpTransportSE ht = new HttpTransportSE(MainService.URL);
		ht.setXmlVersionTag("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		ht.debug = true;
		ht.call(null, envelope);
		if (envelope.getResponse() != null) {
			return true;
		}
		return false;
	}
	
//	/**
//	 * 得到新鲜事涉及到的所有用户的昵称
//	 * @param freshNews
//	 */
//	public static void getAllUserNickNameInFreshNews(List<FreshNews> freshNews) {
//		//获取新鲜事涉及到的用户的头像
//		Set<Long> userIDs = new HashSet<Long>();//得到所有用户ID
//		for (int i = 0; i < freshNews.size(); i++) {
//			long happenTo = freshNews.get(i).getHappenTo();
//			if(!userIDs.contains(happenTo)) {
//				String string = UserService.alluserNickName.get(happenTo); 
//				if(string == null) {
//					HashMap<Object, Object> param2 = new HashMap<Object, Object>();
//					param2.put("id",happenTo);
//					Task task2 = new Task(Task.GET_USER_NICKNAME_BY_ID,param2);
//					MainService.newTask(task2);
//				}
//			};
//			int length = freshNews.get(i).getDiscussList().size();
//			if (length >= 1) {
//				if(length > 3) {length = 3;}
//				for (int j = 0; j < length; j++) {
//					Long whoDiscuss = freshNews.get(i).getDiscussList().get(j).getWhoDiss();
//					if(!userIDs.contains(whoDiscuss)) {
//						String string = UserService.alluserNickName.get(whoDiscuss); 
//						if(string == null) {
//							HashMap<Object, Object> param2 = new HashMap<Object, Object>();
//							param2.put("id",whoDiscuss);
//							Task task2 = new Task(Task.GET_USER_NICKNAME_BY_ID,param2);
//							MainService.newTask(task2);
//						}
//					};
//				}
//			}
//			userIDs.add(happenTo);//此用户头像已经添加，记录
//		}
//	}
	
	/**
	 * 得到新鲜事涉及到的所有用户的头像
	 * @param freshNews
	 */
	public static void getAllUserInfoInFreshNews(List<FreshNews> freshNews) {
		//获取新鲜事涉及到的用户的头像
		Set<Long> userIDs = new HashSet<Long>();//得到所有用户ID
		for (int i = 0; i < freshNews.size(); i++) {
			long happenTo = freshNews.get(i).getHappenTo();
			if(!userIDs.contains(happenTo)) {
				BitmapDrawable bd = UserService.alluserIcon.get(happenTo); 
				if(bd == null) {
					HashMap<Object, Object> param2 = new HashMap<Object, Object>();
					param2.put("id",happenTo);
					Task task2 = new Task(Task.GET_USER_AVATAR_PATH_BY_ID,param2);
					MainService.newTask(task2);
				}
				String string = UserService.alluserNickName.get(happenTo); 
				if(string == null) {
					HashMap<Object, Object> param2 = new HashMap<Object, Object>();
					param2.put("id",happenTo);
					Task task2 = new Task(Task.GET_USER_NICKNAME_BY_ID,param2);
					MainService.newTask(task2);
				}
			};
			int length = freshNews.get(i).getDiscussList().size();
			if (length >= 1) {
				if(length > 3) {length = 3;}
				for (int j = 0; j < length; j++) {
					Long whoDiscuss = freshNews.get(i).getDiscussList().get(j).getWhoDiss();
					if(!userIDs.contains(whoDiscuss)) {
						BitmapDrawable bd1 = UserService.alluserIcon.get(whoDiscuss); 
						if(bd1 == null) {
							HashMap<Object, Object> param2 = new HashMap<Object, Object>();
							param2.put("id",whoDiscuss);
							Task task2 = new Task(Task.GET_USER_AVATAR_PATH_BY_ID,param2);
							MainService.newTask(task2);
						}
						String string = UserService.alluserNickName.get(whoDiscuss); 
						if(string == null) {
							HashMap<Object, Object> param2 = new HashMap<Object, Object>();
							param2.put("id",whoDiscuss);
							Task task2 = new Task(Task.GET_USER_NICKNAME_BY_ID,param2);
							MainService.newTask(task2);
						}
					};
				}
			}
			userIDs.add(happenTo);//此用户头像已经添加，记录
		}
	}
	
	public static void addNewFriends(Long userID, Long id) throws XmlPullParserException, IOException{
		String METHOD_NAME = "addNewFriends";
		SoapObject soapObject = new SoapObject(MainService.NAMESPACE, METHOD_NAME);
		soapObject.addProperty("arg0", userID);
		soapObject.addProperty("arg1", id);
		//生成调用webService方法的SOAP请求信息。
		NetUtil.useEnvelope(soapObject);
	}
	
	/**
	 * 用户发表一个新心情
	 * @param userID
	 * @param content
	 * @return
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	public static void addANewGibberish(Long userID, String content) throws XmlPullParserException, IOException{
		String METHOD_NAME = "addANewGibberish";
		SoapObject soapObject = new SoapObject(MainService.NAMESPACE, METHOD_NAME);
		soapObject.addProperty("arg0", userID);
		soapObject.addProperty("arg1", content);
		//生成调用webService方法的SOAP请求信息。
		NetUtil.useEnvelope(soapObject);
	}
	
	/**
	 * 评论其他用户心情
	 * @param whoDiss
	 * @param discussContent
	 * @param index
	 * @return
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	public static void discussToUserGibberish(Long whoDiss, String discussContent, Long index) throws XmlPullParserException, IOException{
		String METHOD_NAME = "discussToUserGibberish";
		SoapObject soapObject = new SoapObject(MainService.NAMESPACE, METHOD_NAME);
		soapObject.addProperty("arg0", whoDiss);
		soapObject.addProperty("arg1", discussContent);
		soapObject.addProperty("arg2", index);
		//生成调用webService方法的SOAP请求信息
		NetUtil.useEnvelope(soapObject);
	}
	
	/**
	 * 根据ID得到用户头像地址
	 * @param id
	 * @return
	 * @throws IOException
	 * @throws XmlPullParserException
	 */
	public static String getUserAvatarPathById(Long id) throws IOException, XmlPullParserException{
		String METHOD_NAME = "getUserAvatarPathById";
		SoapObject soapObject = new SoapObject(MainService.NAMESPACE, METHOD_NAME);
		soapObject.addProperty("arg0", id);
		//生成调用webService方法的SOAP请求信息
		return NetUtil.useEnvelope(soapObject).toString();
	}
	
	/**
	 * 根据ID得到用户昵称
	 * @param id
	 * @return
	 * @throws IOException
	 * @throws XmlPullParserException
	 */
	public static String getUserNickNameById(Long id) throws IOException, XmlPullParserException{
		String METHOD_NAME = "getUserNickNameById";
		SoapObject soapObject = new SoapObject(MainService.NAMESPACE, METHOD_NAME);
		soapObject.addProperty("arg0", id);
		//生成调用webService方法的SOAP请求信息
		return NetUtil.useEnvelope(soapObject).toString();
	}
	
	/**
	 * 写影评
	 * @param userID
	 * @param content
	 * @param movieID
	 * @return
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	public static void writeFilmReview(Long userID, String content, Long movieID) throws XmlPullParserException, IOException{
		String METHOD_NAME = "writeFilmReview";
		SoapObject soapObject = new SoapObject(MainService.NAMESPACE, METHOD_NAME);
		soapObject.addProperty("arg0", userID);
		soapObject.addProperty("arg1", content);
		soapObject.addProperty("arg2", movieID);
		//生成调用webService方法的SOAP请求信息。
		NetUtil.useEnvelope(soapObject);
	}
	
	/**
	 * 解析得到的用户实例
	 * @param result
	 * @return
	 */
	public static User parseUser(SoapObject result){
		Long id = Long.valueOf(result.getProperty("id").toString());
		String avatarLink = result.getProperty("avatarLink").toString();
		String email = result.getProperty("email").toString();
		String password = result.getProperty("password").toString();
		String nickName = result.getProperty("nickName").toString();
		String newsTypes = result.getProperty("newsTypes").toString();
		User user = new User();
		user.setId(id);
		user.setAvatarLink(avatarLink);
		user.setEmail(email);
		user.setNewsTypes(newsTypes);
		user.setNickName(nickName);
		user.setPassword(password);
		return user;
	}

	public static void collectMovie(Long movieID3, Long userID7, Double score) throws IOException, XmlPullParserException {
		String METHOD_NAME = "collectMovie";
		SoapObject soapObject = new SoapObject(MainService.NAMESPACE, METHOD_NAME);
		soapObject.addProperty("arg0", movieID3);
		soapObject.addProperty("arg1", userID7);
		soapObject.addProperty("arg2", score);
		Marshal floatMarshal = new MarshalFloat();
		//生成调用webService方法的SOAP请求信息。
		MySoapEnvelope envelope = new MySoapEnvelope(SoapEnvelope.VER11);
		floatMarshal.register(envelope);
		envelope.encodingStyle = "utf-8";
		envelope.dotNet = false;
		envelope.setAddAdornments(false);
		envelope.implicitTypes = true;
		envelope.setOutputSoapObject(soapObject);
		HttpTransportSE ht = new HttpTransportSE(MainService.URL);
		ht.setXmlVersionTag("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		ht.debug = true;
		ht.call(null, envelope);
	}
}
