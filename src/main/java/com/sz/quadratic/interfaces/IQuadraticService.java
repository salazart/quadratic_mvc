package com.sz.quadratic.interfaces;

import com.sz.quadratic.models.Quadratic;

import java.util.List;

public interface IQuadraticService extends ICacheable{
	
	double getFirstResult(Quadratic quadratic);
	
	double getSecondResult(Quadratic quadratic);

	List<Quadratic> getAllQuadratics();

	Quadratic saveQuadratic(Quadratic quadratic);

	Quadratic readQuadratic(Long id);

	List<Quadratic> getQuadraticsByCoefficients(Quadratic quadratic);
}
