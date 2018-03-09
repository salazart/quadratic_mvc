package com.sz.quadratic.dao.interfaces;

import java.io.Serializable;

import com.sz.quadratic.exceptions.QuadraticException;
import com.sz.quadratic.interfaces.IEntity;
import com.sz.quadratic.models.Quadratic;

public interface IHibernateDAO <T extends IEntity, P extends Serializable>{
	T create(T entity) throws QuadraticException;
	
	T read(P id) throws QuadraticException;
	
	T update(T entity) throws QuadraticException;
	
	void delete(T entity) throws QuadraticException;
}
