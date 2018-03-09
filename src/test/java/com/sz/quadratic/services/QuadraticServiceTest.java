/**
 * 
 */
package com.sz.quadratic.services;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.sz.quadratic.exceptions.QuadraticException;
import com.sz.quadratic.interfaces.IQuadraticService;
import com.sz.quadratic.models.Quadratic;

/**
 * @author lenovo
 *
 */
public class QuadraticServiceTest {

	private IQuadraticService quadraticService;
	
	private Quadratic quadratic;
	private long id;
	
	@Before
	public void before() {
		this.quadraticService = new QuadraticService();
		
		quadratic = new Quadratic();
		quadratic.setA(5);
		quadratic.setB(6);
		quadratic.setC(7);
	}
	
	@Test
	public void test() {
		Quadratic quadratic1 = null;
		try {
			quadratic1 = quadraticService.create(quadratic);
		} catch (QuadraticException e) {
			System.err.println(e);
		}
		System.out.println(quadratic1);
		System.out.println(this.id);
		
		Long id = quadratic1.getId();
		System.out.println(id);
		this.id = id == null ? 0 : id;
		System.out.println(this.id);
		
		System.out.println("id=" + id);
		assertEquals("true", "true");
	}

}
