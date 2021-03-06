package net.doudouer.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import net.doudouer.util.StringUtil;

public class User implements Serializable, KvmSerializable{

	private String newsTypes = "";
	
	private static final long serialVersionUID = 5378001079989767351L;
	
	private Long id;
	private String email;
	private String nickName;
	private String password;
	private Long timestamp;
	
	// 用户的 “新鲜事” 内容类型
	private String[] newsTypeArray = new String[]{"addFriend","recordGibberish","writeFilmReview","collectFilm","discussReview"};
	
	/** 我的电影收藏 **/
	private Set<Movie> movieCollection = new HashSet<Movie>();
	
	// 头像地址
	private String avatarLink;
	
	public Set<Movie> getMovieCollection() {
		return movieCollection;
	}

	public void setMovieCollection(Set<Movie> movieCollection) {
		this.movieCollection = movieCollection;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getNewsTypes() {
		if(newsTypeArray != null){
			return StringUtil.connectString(newsTypeArray, "|");
		}else {
			return "";
		}
	}

	public void setNewsTypes(String newsTypes) {
		this.newsTypeArray = newsTypes.split("\\|");
	}

	public String[] getNewsTypeArray() {
		return newsTypeArray;
	}

	public void setNewsTypeArray(String[] newsTypeArray) {
		this.newsTypeArray = newsTypeArray;
	}

	public String getAvatarLink() {
		return avatarLink;
	}

	public void setAvatarLink(String avatarLink) {
		this.avatarLink = avatarLink;
	}

	private int propertyCount = 4;
	
	@Override
	public Object getProperty(int arg0) {
		switch (arg0){
//        case 0:
//            return "";//avatarLink
        case 0:
            return email;
//        case 2:
//            return "";//id
//        case 3:
//        	return "";//newsTypeArray
//        case 4:
//        	return newsTypes;
        case 1:
        	return nickName;
        case 2:
        	return password;
        case 3:
        	return timestamp;
        default:
            return null;
        }
	}

	@Override
	public int getPropertyCount() {
		return propertyCount;
	}

	@Override
	public void getPropertyInfo(int arg0, Hashtable arg1, PropertyInfo pi) {
		switch (arg0){
//        case 0:
//        	pi.type = PropertyInfo.STRING_CLASS;
//            pi.name = "avatarLink";
//            break;
        case 0:
        	pi.type = PropertyInfo.STRING_CLASS;
            pi.name = "email";
            break;
//        case 2:
//        	pi.type = PropertyInfo.LONG_CLASS;
//            pi.name = "id";
//            break;
//        case 3:
//        	pi.type = PropertyInfo.STRING_CLASS;
//            pi.name = "newsTypeArray";
//            break;
//        case 4:
//        	pi.type = PropertyInfo.STRING_CLASS;
//            pi.name = "newsTypes";
//            break;
        case 1:
        	pi.type = PropertyInfo.STRING_CLASS;
            pi.name = "nickName";
            break;
        case 2:
        	pi.type = PropertyInfo.STRING_CLASS;
            pi.name = "password";
            break;
        case 3:
        	pi.type = PropertyInfo.LONG_CLASS;
            pi.name = "timestamp";
            break;
        default:
        	break;
		}
	}

	@Override
	public void setProperty(int arg0, Object object) {
		switch (arg0){
//        case 0:
//            avatarLink = (String)object;
//            break;
        case 0:
        	email = (String)object;
        	break;
//        case 2:
//        	id = (Long)object;
//            break;
//        case 3:
//        	newsTypeArray = (String[])object;
//            break;
//        case 4:
//        	newsTypes = (String)object;
//            break;
        case 1:
        	nickName = (String)object;
            break;
        case 2:
        	password = (String)object;
            break;
        case 3:
        	timestamp = (Long)object;
            break;
        default:
        	break;
		}
	}
	
}
