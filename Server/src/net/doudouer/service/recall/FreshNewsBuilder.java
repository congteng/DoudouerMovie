package net.doudouer.service.recall;

import java.io.Serializable;

import net.doudouer.domain.FreshNews;

/**
 * 重构
 * 抽出发布新鲜事的共同逻辑 建立FreshNewsBuilder接口
 * 
 * 该接口的方法不需要暴露
 * 是给service层自身调用的
 * 
 * @author congteng
 * 
 */
public interface FreshNewsBuilder extends Serializable{
	FreshNews buildNews(Long messageReciver, Long happenTo);
}
