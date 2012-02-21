package net.doudouer.service;

import java.util.List;

import net.doudouer.domain.FreshNews;
import net.doudouer.service.recall.FreshNewsBuilder;

public interface FreshNewsService extends BaseService<FreshNews> {
	
	/**
	 * 为用户的好友添加发生在当前用户身上的事（新鲜事）
	 * @param userID
	 * @param newsType
	 * @param builder
	 */
	void addWhatFreshNewsToWhoseFriendsAndSelf(Long userID, String newsType, FreshNewsBuilder builder);
	
	/**
	 * 获取该用户的新鲜事列表
	 * @param userID 用户id
	 * @param off 从第几个数据开始
	 * @param len 每页的长度
	 * @param desc 是否倒序
	 * @return 返回新鲜事对象
	 */
	List<FreshNews> getFreshNewsForSpecificUser(Long userID, int off, int len, boolean desc);
	
}
