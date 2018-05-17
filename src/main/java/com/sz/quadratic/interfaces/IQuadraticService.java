package com.sz.quadratic.interfaces;

import com.sz.quadratic.dao.interfaces.IHibernateDAO;
import com.sz.quadratic.models.Quadratic;

public interface IQuadraticService extends IHibernateDAO<Quadratic, Long>{
	
	double getFirstResult(Quadratic quadratic);
	
	double getSecondResult(Quadratic quadratic);
	
}
