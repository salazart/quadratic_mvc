package com.sz.quadratic.services;

import org.springframework.stereotype.Service;

import com.sz.quadratic.dao.impl.HibernateDAOImpl;
import com.sz.quadratic.interfaces.IQuadraticService;
import com.sz.quadratic.models.Quadratic;

@Service
public class QuadraticService extends HibernateDAOImpl<Quadratic, Long> implements IQuadraticService{

	@Override
	public double getFirstResult(Quadratic quadratic) {
		double x1 = (-quadratic.getB() + Math.sqrt(quadratic.getDiscriminant())) / (2 * quadratic.getA());
		quadratic.setX1(x1);
		return x1;
	}

	@Override
	public double getSecondResult(Quadratic quadratic) {
		double x2 = (-quadratic.getB() - Math.sqrt(quadratic.getDiscriminant())) / (2 * quadratic.getA());
		quadratic.setX2(x2);
		return x2;
	}
	
}
