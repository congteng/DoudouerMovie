package net.doudouer.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.doudouer.dao.BaseDao;
import net.doudouer.dao.impl.UserDao;
import net.doudouer.domain.FreshNews;
import net.doudouer.domain.FriendRelationship;
import net.doudouer.domain.User;
import net.doudouer.service.FreshNewsService;
import net.doudouer.service.FriendRelationshipService;
import net.doudouer.service.UserService;
import net.doudouer.service.recall.FreshNewsBuilder;

import org.springframework.stereotype.Service;

@Service("friendRelationshipService")
public class FriendRelationshipServiceImpl extends BaseServiceImpl<FriendRelationship> implements FriendRelationshipService {

	@Resource(name = "userDao")
	private UserDao userDao;

	@Resource(name = "friendRelationshipDao")
	public void setDao(BaseDao<FriendRelationship> dao) {
		super.setDao(dao);
	}
	
	/**
	 * 增加1-n个好友， 需要经过认证
	 * @param userID
	 * @param ids
	 */
	public void addNewFriends(final Long userID, List<Long> ids) {
		
		/** 添加好友 **/
		for(final Long id : ids){
			// 非自己
			if(!userID.equals(id)){
				// 双向添加
				FriendRelationship rs_1 = new FriendRelationship();
				rs_1.setUserID(userID);
				rs_1.setFriendID(id);
				
				FriendRelationship rs_2 = new FriendRelationship();
				rs_2.setUserID(id);
				rs_2.setFriendID(userID);
				
				/** 双向添加消息 **/
				// 向 两个用户 分别向其当前的 好友 添加新鲜事
				freshNewsService.addWhatFreshNewsToWhoseFriendsAndSelf(userID, "addFriend", new FreshNewsBuilder(){

					public FreshNews buildNews(Long messageReciver, Long happenTo) {
						FreshNews news = new FreshNews();
						news.setUserID(messageReciver);
						news.setHappenTo(happenTo);
						news.setNewsOfEntity("User");
						news.setNewsID(id);
						news.setNewsType("addFriend");
						news.setTimestamp(System.currentTimeMillis());
						return news;
					}
				});
				
				freshNewsService.addWhatFreshNewsToWhoseFriendsAndSelf(id, "addFriend", new FreshNewsBuilder(){

					public FreshNews buildNews(Long messageReciver, Long happenTo) {
						FreshNews news = new FreshNews();
						news.setUserID(messageReciver);
						news.setHappenTo(happenTo);
						news.setNewsOfEntity("User");
						news.setNewsID(userID);
						news.setNewsType("addFriend");
						news.setTimestamp(System.currentTimeMillis());
						return news;
					}
				});
				
				saveOrUpdateEntity(rs_1);
				saveOrUpdateEntity(rs_2);
			}
		}
	}

	/**
	 * 删除 1-n个好友
	 * @param userID
	 * @param ids
	 */
	public void deleteSomeFriends(Long userID, List<Long> ids) {
		String hql = "from FriendRelationship f where f.userID = ? and f.friendID = ?";
		FriendRelationship rs = null;
		
		for(Long id : ids){
			
			List<FriendRelationship> list = findEntityByHQL(hql, userID, id);
			if(list.size() > 0){
				rs = list.get(0);
			}else{
				continue;
			}
			// 双向删除
			// 构造好友的关系
			FriendRelationship rsReverse = new FriendRelationship();
			rsReverse.setFriendID(userID);
			rsReverse.setUserID(id);
			
			deleteEntity(rs);
			deleteEntity(rsReverse);
			
		}
	}

	/**
	 * 获取用户所有的好友
	 * @param userID
	 * @return
	 */
	public List<User> getAllFriends(Long userID) {
		
		String hql = "from User u where u.id in(select friendID from FriendRelationship where userID = ?)";
		List<User> users = userDao.findEntityByHQL(hql, userID);
		return users;
	}
	
}
