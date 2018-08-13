package com.sz.quadratic.services;

import com.sz.quadratic.dao.impl.HibernateDAOImpl;
import com.sz.quadratic.exceptions.QuadraticException;
import com.sz.quadratic.interfaces.IQuadraticService;
import com.sz.quadratic.models.Quadratic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class QuadraticService extends HibernateDAOImpl<Quadratic, Long> implements IQuadraticService {

	private Logger logger = LogManager.getLogger(getClass());

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
    public List<Quadratic> getAllQuadratics(){
        simulateSlowService();
		try {
			return super.getAll();
		} catch (QuadraticException e) {
			logger.error(e);
			return Collections.emptyList();
		}
	}

	private void simulateSlowService(){
        long time = 3000L;
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            logger.error(e);
        }
    }

	@CacheEvict(value = "quadratic", allEntries=true)
    public Quadratic saveQuadratic(Quadratic quadratic){
		try {
			create(quadratic);
		} catch (QuadraticException e) {
			logger.error(e);
		} finally {
			return quadratic;
		}
	}

	@CacheEvict(value = "quadratic", allEntries=true)
	public void clearCache() {
        System.out.println("clear cache");
    }
}
