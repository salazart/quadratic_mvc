package com.sz.quadratic.dao.interfaces;

import com.sz.quadratic.exceptions.QuadraticException;
import com.sz.quadratic.interfaces.IEntity;
import org.hibernate.Criteria;

import java.io.Serializable;
import java.util.List;

public interface IDAO<T extends IEntity, P extends Serializable>{
	void setClass(Class<T> clazz);

	T create(T entity) throws QuadraticException;
	
	T read(P id) throws QuadraticException;
	
	T update(T entity) throws QuadraticException;
	
	void delete(T entity) throws QuadraticException;

	List getAll() throws QuadraticException;

	List getByCriteria(Criteria criteria);

	Criteria getCriteria();
}
