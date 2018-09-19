package com.sz.quadratic.dao.impl;

import com.sz.quadratic.dao.interfaces.IDAO;
import com.sz.quadratic.interfaces.IEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractHibernateDAO<T extends IEntity, P extends Serializable> implements IDAO<T, P> {
	private Class<T> clazz;
	private Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	protected SessionFactory sessionFactory;

    public void setClass(Class<T> clazz) {
    	this.clazz = clazz;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public T create(T entity) {
		log.debug("Creating entity into db with entity class:" + entity.getClass() + " and entity:" + entity);
		try {
			getCurrentSession().beginTransaction();
			getCurrentSession().persist(entity);
			getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			log.error(e);
		} finally {
			getCurrentSession().close();
		}
		return entity;
	}
	
	public T read(P id) {
		log.debug("Reading entity form db with entity class:" + id.getClass() + " and entity:" + id);
		try {
			getCurrentSession().beginTransaction();
			return getCurrentSession().get(clazz, id);
		} finally {
			getCurrentSession().close();
		}
	}
	
	@Override
	public T update(T entity) {
		log.debug("Updating entity into db with entity class:" + clazz + " and entity:" + entity);
		try {
			getCurrentSession().beginTransaction();
			getCurrentSession().merge(entity);
			getCurrentSession().getTransaction().commit();
		}
		finally {
			getCurrentSession().close();
		}
		return entity;
	}

	@Override
	public void delete(T entity) {
		log.debug("Deleting entity from db entity class:" + clazz + " and entity:" + entity);
		try {
			getCurrentSession().beginTransaction();
			getCurrentSession().delete(entity);
			getCurrentSession().getTransaction().commit();
		} finally {
			getCurrentSession().close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAll() {
		log.debug("Reading list entity form db with entity class:" + clazz);
		try {
			getCurrentSession().beginTransaction();
			return (List<T>) getCurrentSession()
					.createCriteria(clazz)
					.list();
		} finally {
			getCurrentSession().close();
		}
	}

	public Criteria getCriteria(){
		getCurrentSession().beginTransaction();
		return getCurrentSession().createCriteria(clazz);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getByCriteria(Criteria criteria) {
		log.debug("Reading entities by criteria form db with entity class:" + clazz);
		try {
			return (List<T>) criteria.list();
		} finally {
			getCurrentSession().close();
		}
	}
}
