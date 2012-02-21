package net.doudouer.service;

import java.io.Serializable;
import java.util.List;

public interface BaseService<T> {

	// 写操作
	public void saveEntity(T t);
	public void updateEntity(T t);
	public void saveOrUpdateEntity(T t);
	public void deleteEntity(T t);
	public void batchHandleEntities(String hql, Serializable ... serializables);
	
	// 读操作
	public T loadEntity(Serializable id);
	public T getEntity(Serializable id);
	public List<T> findEntityByHQL(String hql, Serializable ... serializables);
	/* 按照hql查询对象,不一定是实体 */
	public List<Object> findObjectByHQL(String hql,Serializable...serializables);
	
	// 单值检索
	public Object uniqueResult(String hql, Serializable ... serializables);
	/* 分页查询 */
	public List<T> findPagingByHQL(String hql,int offset,int length,Serializable...serializables);
	
}
