package com.sz.quadratic.models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class QuadraticTest {
	private static final long ID = 6;
	private static final double A = 1;
	private static final double B = 2;
	private static final double C = 3;
	
	private Quadratic quadratic1;
	private Quadratic quadratic2;
	
	@Before
	public void before() {
		quadratic1 = new Quadratic();
		quadratic1.setId(ID);
		quadratic1.setA(A);
		quadratic1.setB(B);
		quadratic1.setC(C);

		quadratic2 = new Quadratic();
		quadratic2.setId(ID);
		quadratic2.setA(A);
		quadratic2.setB(B);
		quadratic2.setC(C);
	}
	
	@Test
	public void testEqualsObject() {
		assertTrue(quadratic1.equals(quadratic2));
	}

}
