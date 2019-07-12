package com.sz.quadratic.models;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class QuadraticTest {
	private Quadratic q1;

	@Before
	public void before() {
		q1 = new Quadratic(2d,5d,-3d);
	}
	
	@Test
	public void testQuadraticEquals() {
		Quadratic q2 = new Quadratic(2d,5d,-3d);
		assertThat(q1, is(q2));
	}

	@Test
	public void testQuadraticResults() {
		assertThat(q1.getA(), is(2d));
		assertThat(q1.getB(), is(5d));
		assertThat(q1.getC(), is(-3d));
		assertThat(q1.getX1(), is(-3d));
		assertThat(q1.getX2(), is(0.5d));
	}

	@Test
	public void testNotResults() {
		Quadratic q2 = new Quadratic(5d, 3d, 9d);
		assertNull(q2.getX1());
		assertNull(q2.getX2());
	}
}
