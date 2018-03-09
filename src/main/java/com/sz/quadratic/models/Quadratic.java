package com.sz.quadratic.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sz.quadratic.interfaces.IEntity;

@Entity
@Table(name = "quadratic")
public class Quadratic implements IEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	@Column(name = "a")
	private double a;
	@Column(name = "b")
	private double b;
	@Column(name = "c")
	private double c;

	@Column(name = "x1")
	private double x1;
	@Column(name = "x2")
	private double x2;
	
	public String toString() {
		return "quadratic: id:" + id + ", a:" + a + ", b:" + b + ", c:" + c + ", x1:" + x1 + ", x2:" + x2;
	}
	
	@Override
	public boolean equals(Object object) {
		return (object != null)
				&& (object instanceof Quadratic)
				&& ((Quadratic)object).getId() == this.getId()
				&& ((Quadratic)object).getA() == this.getA()
				&& ((Quadratic)object).getB() == this.getB()
				&& ((Quadratic)object).getC() == this.getC();
	}
	
	private double getDiscriminant(){
		return Math.pow(b, 2) - 4 * a * c;
	}
	
	public double getFirstResult(){
		return (-b - Math.sqrt(getDiscriminant())) / (2 * a);
	}
	
	public double getSecondResult(){
		return (-b - Math.sqrt(getDiscriminant())) / (2 * a);
	}
	
	public boolean isResult(){
		return getDiscriminant() >= 0;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public double getA() {
		return a;
	}

	public void setA(double a) {
		this.a = a;
	}

	public double getB() {
		return b;
	}

	public void setB(double b) {
		this.b = b;
	}

	public double getC() {
		return c;
	}

	public void setC(double c) {
		this.c = c;
	}

	public double getX1() {
		return x1;
	}

	public void setX1(double x1) {
		this.x1 = x1;
	}

	public double getX2() {
		return x2;
	}

	public void setX2(double x2) {
		this.x2 = x2;
	}

	
}