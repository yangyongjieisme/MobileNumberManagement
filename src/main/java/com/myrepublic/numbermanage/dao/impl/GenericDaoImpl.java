package com.myrepublic.numbermanage.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.myrepublic.numbermanage.dao.GenericDao;

@Repository
@SuppressWarnings("unchecked")

public class GenericDaoImpl<T extends Serializable, PK extends Serializable> extends HibernateDaoSupport
		implements GenericDao<T, PK> {

	private Class<T> entityClass;
	
	@Resource(name="sessionFactory")

	private void setMySessionFactory(SessionFactory sessionFactory){

	  super.setSessionFactory(sessionFactory);
	 }
	
	public GenericDaoImpl() {
		this.entityClass = null;
		Class c = getClass();
		Type t = c.getGenericSuperclass();
		if (t instanceof ParameterizedType) {
			Type[] p = ((ParameterizedType) t).getActualTypeArguments();
			this.entityClass = (Class<T>) p[0];
		}
		
	}
	 
      
	public T get(PK id) {
		return (T) getHibernateTemplate().get(entityClass, id);
	}

	
	public List<T> loadAll() {
		return (List<T>) getHibernateTemplate().loadAll(entityClass);
	}

	public void update(T entity) {
		getHibernateTemplate().update(entity);
	}

	
	public void save(T entity) {
		getHibernateTemplate().save(entity);
	}

	public void saveOrUpdate(T entity) {
		getHibernateTemplate().saveOrUpdate(entity);
	}

	public void saveOrUpdate(Collection<T> entities) {
		getHibernateTemplate().saveOrUpdate(entities);
	}

	public void delete(T entity) {
		getHibernateTemplate().delete(entity);
	}

	
	public void deleteByKey(PK id) {
		this.delete(this.get(id));
	}

	
	public void deleteAll(Collection<T> entities) {
		getHibernateTemplate().deleteAll(entities);
	}

	public List<T> find(String queryString, Object[] values) {
		Query queryObject = createQueryObject(queryString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				queryObject.setParameter(i, values[i]);
			}
		}
		return queryObject.list();

	}

	public List<T> find(String queryString, Object value) {
		Query queryObject = createQueryObject(queryString);
		queryObject.setParameter(0, value);
		return queryObject.list();

	}

	private Query createQueryObject(String queryString) {

		Query queryObject = getSessionFactory().getCurrentSession().createQuery(queryString);
		return queryObject;
	}

	
}