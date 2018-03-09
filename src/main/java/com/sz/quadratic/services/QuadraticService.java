package com.sz.quadratic.services;

import org.springframework.stereotype.Service;

import com.sz.quadratic.dao.impl.HibernateDAOImpl;
import com.sz.quadratic.interfaces.IQuadraticService;
import com.sz.quadratic.models.Quadratic;

@Service
public class QuadraticService extends HibernateDAOImpl<Quadratic, Long> implements IQuadraticService{

}
