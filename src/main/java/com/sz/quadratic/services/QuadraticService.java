package com.sz.quadratic.services;

import com.sz.quadratic.dao.interfaces.IDAO;
import com.sz.quadratic.exceptions.QuadraticException;
import com.sz.quadratic.interfaces.IQuadraticService;
import com.sz.quadratic.models.Quadratic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.Collections;
import java.util.List;

@Service
@ManagedBean(name = "quadraticService")
public class QuadraticService implements IQuadraticService {

	private Logger log = LogManager.getLogger(getClass());

    @ManagedProperty(value = "#{hibernateDao}")
	private IDAO<Quadratic, Long> dao;

	@Autowired
	public void setDao(IDAO<Quadratic, Long> dao){
		this.dao = dao;
		this.dao.setClass(Quadratic.class);
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
		} finally {
			return quadratic;
		}
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

    @CacheEvict(value = "quadratic", allEntries = true)
	public void clearCache() {
		log.debug("Cache cleared successfully.");
	}
}
