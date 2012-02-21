package net.doudouer.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import net.doudouer.dao.BaseDao;
import net.doudouer.dao.impl.FreshNewsDao;
import net.doudouer.service.BaseService;
import net.doudouer.service.FreshNewsService;

public abstract class BaseServiceImpl<T> implements BaseService<T> {

	private BaseDao<T> dao;

	@Resource(name = "freshNewsService")
	protected FreshNewsService freshNewsService;

	@Resource
	public void setDao(BaseDao<T> dao) {
		this.dao = dao;
	}

	public void batchHandleEntities(String hql, Serializable... serializables) {
		dao.batchHandleEntities(hql, serializables);
	}

	public void deleteEntity(T t) {
		dao.deleteEntity(t);
	}

	public List<T> findEntityByHQL(String hql, Serializable... serializables) {
		return dao.findEntityByHQL(hql, serializables);
	}

	public T getEntity(Serializable id) {
		return dao.getEntity(id);
	}

	public T loadEntity(Serializable id) {
		return dao.loadEntity(id);
	}

	public void saveEntity(T t) {
		dao.saveEntity(t);
	}

	public void saveOrUpdateEntity(T t) {
		dao.saveOrUpdateEntity(t);
	}

	public Object uniqueResult(String hql, Serializable... serializables) {
		return dao.uniqueResult(hql, serializables);
	}

	public void updateEntity(T t) {
		dao.updateEntity(t);
	}

	public List<Object> findObjectByHQL(String hql, Serializable... serializables) {
		return dao.findObjectByHQL(hql, serializables);
	}

	/**
	 * 分页查询
	 */
	public List<T> findPagingByHQL(String hql, int offset, int length, Serializable... serializables) {
		return dao.findPagingByHQL(hql, offset, length, serializables);
	}
}
