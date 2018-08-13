package com.sz.quadratic.interfaces;

import com.sz.quadratic.dao.interfaces.IHibernateDAO;
import com.sz.quadratic.models.Quadratic;

import java.util.List;

public interface IQuadraticService extends IHibernateDAO<Quadratic, Long>, ICacheable{
	
	double getFirstResult(Quadratic quadratic);
	
	double getSecondResult(Quadratic quadratic);

	List<Quadratic> getAllQuadratics();

	Quadratic saveQuadratic(Quadratic quadratic);
}
