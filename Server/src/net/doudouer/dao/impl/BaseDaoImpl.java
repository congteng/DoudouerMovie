package net.doudouer.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import net.doudouer.dao.BaseDao;

@SuppressWarnings("unchecked")
public abstract class BaseDaoImpl<T> implements BaseDao<T> {

	private SessionFactory sessionFactory;
	
	private Class<T> clazz;
	
	public BaseDaoImpl() {
		ParameterizedType types = (ParameterizedType) getClass().getGenericSuperclass();
		clazz = (Class<T>) types.getActualTypeArguments()[0];
	}
	
	public void batchHandleEntities(String hql, Serializable... serializables) {
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		for(int i = 0 ; i < serializables.length ; i ++){
			q.setParameter(i, serializables[i]);
		}
		q.executeUpdate();
	}

	public void deleteEntity(T t) {
		sessionFactory.getCurrentSession().delete(t);
	}

	public List<T> findEntityByHQL(String hql, Serializable... serializables) {
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		for (int i = 0; i < serializables.length; i++) {
			query.setParameter(i, serializables[i]);
		}
		return query.list();
	}

	public T getEntity(Serializable id) {
		return (T) sessionFactory.getCurrentSession().get(clazz, id);
	}

	public T loadEntity(Serializable id) {
		return (T) sessionFactory.getCurrentSession().load(clazz, id);
	}

	public void saveEntity(T t) {
		sessionFactory.getCurrentSession().save(t);
	}

	public void saveOrUpdateEntity(T t) {
		sessionFactory.getCurrentSession().saveOrUpdate(t);
	}

	public Object uniqueResult(String hql, Serializable... serializables) {
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		for (int i = 0; i < serializables.length; i++) {
			q.setParameter(i, serializables[i]);
		}
		return q.uniqueResult();
	}

	public void updateEntity(T t) {
		sessionFactory.getCurrentSession().update(t);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public List<Object> findObjectByHQL(String hql, Serializable... serializables) {
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		for (int i = 0; i < serializables.length; i++) {
			q.setParameter(i, serializables[i]);
		}
		return q.list();
	}

	/**
	 * 分页查询
	 */
	public List<T> findPagingByHQL(String hql, int offset, int length, Serializable... serializables) {
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		for (int i = 0; i < serializables.length; i++) {
			q.setParameter(i, serializables[i]);
		}
		q.setFirstResult(offset);
		q.setMaxResults(length);
		return q.list();
	}
	/**
	 * 注入会话工厂
	 */
	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
