package net.doudouer.service;

import java.util.List;

import net.doudouer.domain.FriendRelationship;
import net.doudouer.domain.User;

public interface FriendRelationshipService extends BaseService<FriendRelationship> {
	
	/**
	 * 增加1-n个好友
	 * @param userID
	 * @param ids
	 */
	void addNewFriends(Long userID, List<Long> ids);
	
	/**
	 * 删除 1-n个好友
	 * @param userID
	 * @param ids
	 */
	void deleteSomeFriends(Long userID, List<Long> ids);

	/**
	 * 获取用户所有的好友
	 * @param userID
	 * @return
	 */
	List<User> getAllFriends(Long userID);
	
}
