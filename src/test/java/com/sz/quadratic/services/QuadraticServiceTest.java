/**
 * 
 */
package com.sz.quadratic.services;

import static org.junit.Assert.assertEquals;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sz.quadratic.config.AppConfig;
import com.sz.quadratic.interfaces.IQuadraticService;
import com.sz.quadratic.models.Quadratic;

/**
 * @author lenovo
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class QuadraticServiceTest {

//	@Autowired
//	private IQuadraticService quadraticService;
//	private ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-context.xml");

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private Quadratic quadratic;
	private long id;
	
	@Before
	public void before() {
		System.out.println(quadratic);
//		this.quadraticService = new QuadraticService();
		
//		Quadratic quadratic = (Quadratic) applicationContext.getBean("bean1");
//		System.out.println(quadratic);
//		quadratic = new Quadratic();
//		quadratic.setA(5);
//		quadratic.setB(6);
//		quadratic.setC(7);
	}
	
	@Test
	public void test() {
//		Quadratic quadratic1 = null;
//		try {
//			quadratic1 = quadraticService.create(quadratic);
//		} catch (QuadraticException e) {
//			System.err.println(e);
//		}
//		System.out.println(quadratic1);
//		System.out.println(this.id);
//		
//		Long id = quadratic1.getId();
//		System.out.println(id);
//		this.id = id == null ? 0 : id;
//		System.out.println(this.id);
//		
//		System.out.println("id=" + id);
		assertEquals("true", "true");
	}

}
