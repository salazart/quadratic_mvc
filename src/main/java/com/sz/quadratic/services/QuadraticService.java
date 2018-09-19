package com.sz.quadratic.services;

import com.sz.quadratic.dao.interfaces.IDAO;
import com.sz.quadratic.exceptions.QuadraticException;
import com.sz.quadratic.interfaces.IQuadraticService;
import com.sz.quadratic.models.Quadratic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class QuadraticService implements IQuadraticService {

	private Logger log = LogManager.getLogger(getClass());

	private IDAO<Quadratic, Long> dao;

	@Autowired
	public void setDao(IDAO<Quadratic, Long> dao){
		this.dao = dao;
		this.dao.setClass(Quadratic.class);
	}

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

	@Cacheable(value = "quadratic")
	public List<Quadratic> getAllQuadratics() {
		try {
			return dao.getAll();
		} catch (QuadraticException e) {
			log.error(e);
			return Collections.emptyList();
		}
	}

	@CacheEvict(value = "quadratic", allEntries = true)
	public Quadratic saveQuadratic(Quadratic quadratic) {
		try {
			log.info("Try create Quadratic: " + quadratic);
			dao.create(quadratic);
			log.info("Object Quadratic created successfully: " + quadratic);
		} catch (QuadraticException e) {
			log.error(e);
		}
		return quadratic;
	}

    @Override
    public Quadratic readQuadratic(Long id) {
        try {
            return dao.read(id);
        } catch (QuadraticException e) {
            log.error(e);
            return null;
        }
    }

	@Override
	public List<Quadratic> getQuadraticsByCoefficients(Quadratic quadratic) {
		Criteria criteria = dao.getCriteria();
		criteria.add(Restrictions.eq("a", quadratic.getA()));
		criteria.add(Restrictions.eq("b", quadratic.getB()));
		criteria.add(Restrictions.eq("c", quadratic.getC()));
		return dao.getByCriteria(criteria);
	}

	@CacheEvict(value = "quadratic", allEntries = true)
	public void clearCache() {
		log.debug("Cache cleared successfully.");
	}
}
