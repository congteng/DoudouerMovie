package net.doudouer.service.recall;

import java.util.List;

import net.doudouer.domain.FreshNews;

public interface FreshNewsAchiever {

	/**
	 * 通过FreshNews提供的 基本信息 构造 显示在用户 主页上的 完整新鲜事
	 * @param newsList
	 * @return
	 */
	List<FreshNews> generateNewsList(List<FreshNews> newsList);
	
}
