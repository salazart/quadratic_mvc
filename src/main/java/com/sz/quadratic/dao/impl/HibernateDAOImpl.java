package com.sz.quadratic.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.sz.quadratic.dao.interfaces.IHibernateDAO;
import com.sz.quadratic.exceptions.QuadraticException;
import com.sz.quadratic.interfaces.IEntity;

public abstract class HibernateDAOImpl<T extends IEntity, P extends Serializable> implements IHibernateDAO<T, P>{
	protected Class<T> clazz;
	
	private Logger logger = LogManager.getLogger(getClass());
	
	@Autowired
	@Qualifier("localSessionFactory")
	protected SessionFactory sessionFactory;
	
	public HibernateDAOImpl() {
		this.clazz = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	protected final Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public T create(T entity) throws QuadraticException{
		logger.debug("Creating entity into db with entity class:" + entity.getClass() + " and entity:" + entity);
		try {
			getCurrentSession().beginTransaction();
			getCurrentSession().persist(entity);
			getCurrentSession().getTransaction().commit();
		} catch (Exception e) {
			logger.error(e);
		} finally {
			getCurrentSession().close();
		}
		return entity;
	}
	
	public T read(P id) throws QuadraticException {
		logger.debug("Reading entity form db with entity class:" + id.getClass() + " and entity:" + id);
		try {
			getCurrentSession().beginTransaction();
			return (T) getCurrentSession().get(clazz, id);
		} finally {
			getCurrentSession().close();
		}
	}
	
	@Override
	public T update(T entity) throws QuadraticException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(T entity) throws QuadraticException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<T> getAll() throws QuadraticException {
		logger.debug("Reading list entity form db with entity class:" + clazz.getClass());
		try {
			getCurrentSession().beginTransaction();
			return getCurrentSession()
					.createQuery("from " + clazz.getName())
					.list();
		} finally {
			getCurrentSession().close();
		}
	}
}
