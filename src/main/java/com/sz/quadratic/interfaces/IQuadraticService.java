package com.sz.quadratic.interfaces;

import com.sz.quadratic.models.Quadratic;

import java.util.List;

public interface IQuadraticService extends ICacheable{
	
	void calculateResult(Quadratic quadratic);

	boolean isResult(Quadratic quadratic);
	
	List<Quadratic> getAllQuadratics();

	Quadratic saveQuadratic(Quadratic quadratic);

	Quadratic readQuadratic(Long id);

	List<Quadratic> getQuadraticsByCoefficients(Quadratic quadratic);
}
