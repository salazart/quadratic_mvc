/**
 * 
 */
package com.sz.quadratic.services;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sz.quadratic.interfaces.IQuadraticService;
import com.sz.quadratic.models.Quadratic;

import java.util.Arrays;
import java.util.List;

/**
 * @author Alexandr Sydorchuk
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring-context-for-test.xml")
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
		Quadratic q = new Quadratic(A, B, C);
		logger.debug("Try create quadratic:" + q);
		Quadratic quadraticResult = quadraticService.saveQuadratic(q);
		logger.info("OK created quadratic:" + q);

		assertTrue(quadraticResult.getA().equals(q.getA())
				&& quadraticResult.getB().equals(q.getB())
				&& quadraticResult.getC().equals(q.getC())
				&& quadraticResult.getId() != 0);
	}
	
	@Test
	public void createReadQuadraticTest() {
		Quadratic q = new Quadratic(A, B, C);
		logger.debug("Try create quadratic:" + q);
		q = quadraticService.saveQuadratic(q);
		logger.info("OK created quadratic:" + q);

		logger.debug("Try read quadratic by id:" + q.getId());
		Quadratic quadraticResult = quadraticService.readQuadratic(q.getId());
		logger.info("OK readed quadratic by id:" + q);

		assertEquals(q, quadraticResult);
	}

	@Test
	public void createGetAllQuadraticTest() {
		List<Quadratic> quadratics = Arrays.asList(new Quadratic(A, B, C),
				new Quadratic(A + 1, B + 1, C + 1),
				new Quadratic(A + 2, B + 2, C + 2));
		for (Quadratic q : quadratics) {
			logger.debug("Try create quadratic:" + q);
			q = quadraticService.saveQuadratic(q);
			logger.info("OK created quadratic:" + q);
		}

		logger.debug("Try get all quadratics");
        List<Quadratic>	quadraticResult = quadraticService.getAllQuadratics();
        logger.info("OK got all quadratics");
		for (Quadratic quadratic : quadraticResult) {
			logger.info(quadratic);
		}
		logger.info("OK got all quadratics");

		assertThat(quadraticResult.size(), is(3));
	}
}
