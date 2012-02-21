package net.doudouer.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.doudouer.dao.BaseDao;
import net.doudouer.dao.impl.FriendRelationshipDao;
import net.doudouer.dao.impl.UserDao;
import net.doudouer.domain.FreshNews;
import net.doudouer.service.FreshNewsService;
import net.doudouer.service.recall.FreshNewsAchiever;
import net.doudouer.service.recall.FreshNewsBuilder;

import org.springframework.stereotype.Service;


@Service("freshNewsService")
public class FreshNewsServiceImpl extends BaseServiceImpl<FreshNews> implements FreshNewsService {

	@Resource(name = "freshNewsDao")
	public void setDao(BaseDao<FreshNews> dao) {
		super.setDao(dao);
	}
	
	@Resource(name = "friendRelationshipDao")
	private FriendRelationshipDao friendRelationshipDao;
	
	@Resource(name = "userDao")
	private UserDao userDao;
	
	@Resource(name = "freshNewsAchiever")
	private FreshNewsAchiever freshNewsAchiever;

	/**
	 * 为用户的好友添加发生在当前用户身上的事（新鲜事）
	 * @param userID
	 * @param newsType
	 * @param builder
	 */
	public void addWhatFreshNewsToWhoseFriendsAndSelf(Long userID, String newsType, FreshNewsBuilder builder) {
		// 获取当前用户拥有的好友的ID, 为后面的新鲜事准备
		String hql = "select f.friendID from FriendRelationship f where f.userID = ?";
		List<Object> friendIDs = friendRelationshipDao.findObjectByHQL(hql, userID);
		
		// 给自己的新鲜事列表也添加该信息
		friendIDs.add(userID);
		/** 发布信息 **/
		// 添加新鲜事
		for(Object fid : friendIDs){
			String hql_ = "select u.newsTypes from User u where u.id = ?";
			List<Object> types = userDao.findObjectByHQL(hql_, (Long)fid);
			
			String allowTypes = "";
			if(types.size() > 0){
				allowTypes = (String) types.get(0);
			}else{
				continue;
			}
			
			// 是否可以发布该信息
			if(allowTypes.contains(newsType)){
				
				FreshNews news = builder.buildNews((Long) fid, userID);
				
				saveEntity(news);
			}
		}
	}
	
	/**
	 * 获取该用户的新鲜事列表
	 * @param userID 用户id
	 * @param off 从第几个数据开始
	 * @param len 每页的长度
	 * @param desc 是否倒序 默认为true
	 * @return 返回新鲜事对象 
	 */
	public List<FreshNews> getFreshNewsForSpecificUser(Long userID, int off, int len, boolean desc){
		
		List<FreshNews> newsList = 	findPagingByHQL("from FreshNews f where f.userID = ? order by f.timestamp " + (desc ? "desc" : "asc"), off, len, userID);
		
		newsList = freshNewsAchiever.generateNewsList(newsList);
		
		return newsList;
	}
	
	
}
