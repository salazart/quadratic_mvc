package com.sz.quadratic.interfaces;

import com.sz.quadratic.models.Quadratic;

import java.util.List;

public interface IQuadraticService extends ICacheable{
	List<Quadratic> getAllQuadratics();

	Quadratic saveQuadratic(Quadratic quadratic);

	Quadratic readQuadratic(Long id);
}
