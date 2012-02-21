package net.doudouer.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

/**
 * 
 * 设计思路:
 * 
 * 加好友：
 * 
 * UserService{
 * 	addNewFriend{
 * 		FreshNewsService.insert(
 * 			userID : friendIds, 
 * 			happenTo : userId
 * 			type : "addFriend"
 * 			timestamp : xxx
 * 		);
 * 	}
 * }
 * 
 * 发心情:
 * 
 * addNewGibberish(){
 * 	FreshNewsService.insert(
 * 		userID : friendIds, 
 * 		happenTo : userId
 * 		type : "recordGibberish"
 * 		newsOfTable : "UserGibberish"
 * 		newsID : xL
 * 		timestamp : xxx
 * 	);
 * }
 * 
 */
public class FreshNews implements Serializable, KvmSerializable{

	private static final long serialVersionUID = 8479961417744548601L;
	
	/** 以下是 要持久化到 数据库中的 内容 **/
	private Long id;
	// 当前新鲜事 属于哪个 用户 
	private Long userID;
	// 新鲜事是谁发生的
	private Long happenTo;
	// 新鲜事所在的实体名
	private String newsOfEntity;
	// 新鲜事在所在表的 id
	private Long newsID;
	
	/* 新鲜事的评论列表 */
	private List<Discuss> discussList = new ArrayList<Discuss>();
	
	/** 以下是 保存在内存 内容 **/
	private String content;
	
	// 新鲜事类型
	private String newsType;
	private Long timestamp;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public Long getHappenTo() {
		return happenTo;
	}

	public void setHappenTo(Long happenTo) {
		this.happenTo = happenTo;
	}

	public String getNewsOfEntity() {
		return newsOfEntity;
	}

	public void setNewsOfEntity(String newsOfEntity) {
		this.newsOfEntity = newsOfEntity;
	}

	public Long getNewsID() {
		return newsID;
	}

	public void setNewsID(Long newsID) {
		this.newsID = newsID;
	}

	public String getNewsType() {
		return newsType;
	}

	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<Discuss> getDiscussList() {
		return discussList;
	}

	public void setDiscussList(List<Discuss> discussList) {
		this.discussList = discussList;
	}

	@Override
	public Object getProperty(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPropertyCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void getPropertyInfo(int arg0, Hashtable arg1, PropertyInfo arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setProperty(int arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
