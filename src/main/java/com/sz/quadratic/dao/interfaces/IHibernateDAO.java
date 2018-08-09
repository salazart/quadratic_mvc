package com.sz.quadratic.dao.interfaces;

import java.io.Serializable;
import java.util.List;

import com.sz.quadratic.exceptions.QuadraticException;
import com.sz.quadratic.interfaces.IEntity;

public interface IHibernateDAO <T extends IEntity, P extends Serializable>{
	T create(T entity) throws QuadraticException;
	
	T read(P id) throws QuadraticException;
	
	T update(T entity) throws QuadraticException;
	
	void delete(T entity) throws QuadraticException;

	List<T> getAll() throws QuadraticException;
}
