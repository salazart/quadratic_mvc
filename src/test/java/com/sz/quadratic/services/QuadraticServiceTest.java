/**
 * 
 */
package com.sz.quadratic.services;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sz.quadratic.exceptions.QuadraticException;
import com.sz.quadratic.interfaces.IQuadraticService;
import com.sz.quadratic.models.Quadratic;

import java.util.Arrays;
import java.util.List;

/**
 * @author lenovo
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring-context-test.xml")
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
public class QuadraticServiceTest {
	private static final double A = 5;
	private static final double B = 6;
	private static final double C = 7;
	
	private Logger logger = LogManager.getLogger(getClass());

	@Autowired
	private IQuadraticService quadraticService;

	@Test
	public void createQuadraticTest() {
		Quadratic quadratic = new Quadratic(A, B, C);
		logger.debug("Try create quadratic:" + quadratic);
		Quadratic quadraticResult = null;
		try {
			quadraticResult = quadraticService.create(quadratic);
			logger.info("OK created quadratic:" + quadratic);
		} catch (QuadraticException e) {
			logger.error(e);
		}

		assertTrue(quadraticResult.getA() == quadratic.getA() 
				&& quadraticResult.getB() == quadratic.getB()
				&& quadraticResult.getC() == quadratic.getC() 
				&& quadraticResult.getId() != 0);
	}
	
	@Test
	public void createReadQuadraticTest() {
		Quadratic quadratic = new Quadratic(A, B, C);
		logger.debug("Try create quadratic:" + quadratic);
		try {
			quadratic = quadraticService.create(quadratic);
			logger.info("OK created quadratic:" + quadratic);
		} catch (QuadraticException e) {
			logger.error(e);
		}
		Quadratic quadraticResult = null;
		try {
			logger.debug("Try read quadratic by id:" + quadratic.getId());
			quadraticResult = quadraticService.read(quadratic.getId());
			logger.info("OK readed quadratic by id:" + quadratic);
		} catch (QuadraticException e) {
			logger.error(e);
		}
		assertTrue(quadratic.equals(quadraticResult));
	}

	@Test
	public void createGetAllQuadraticTest() {
		List<Quadratic> quadratics = Arrays.asList(new Quadratic(A, B, C),
				new Quadratic(A + 1, B + 1, C + 1),
				new Quadratic(A + 2, B + 2, C + 2));
		for (Quadratic quadratic : quadratics) {
			logger.debug("Try create quadratic:" + quadratic);
			try {
				quadratic = quadraticService.create(quadratic);
				logger.info("OK created quadratic:" + quadratic);
			} catch (QuadraticException e) {
				logger.error(e);
			}
		}

		List<Quadratic> quadraticResult = null;
		try {
			logger.debug("Try get all quadratics");
			quadraticResult = quadraticService.getAll();
			logger.info("OK got all quadratics");
			for (Quadratic quadratic : quadraticResult) {
				logger.info(quadratic);
			}
			logger.info("OK got all quadratics");
		} catch (QuadraticException e) {
			logger.error(e);
		}
		assertThat(quadraticResult.size(), is(3));
	}
	
}
