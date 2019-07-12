package com.sz.quadratic.models;

import com.sz.quadratic.interfaces.IEntity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "quadratic")
public class Quadratic implements IEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "a")
	private Double a;
	@Column(name = "b")
	private Double b;
	@Column(name = "c")
	private Double c;
	@Column(name = "x1")
	private Double x1;
	@Column(name = "x2")
	private Double x2;
	
	public Quadratic() {}

	public Quadratic(Double a, Double b, Double c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("Quadratic{");
		sb.append("id=").append(id)
		.append(", a=").append(a)
		.append(", b=").append(b)
		.append(", c=").append(c)
		.append(", x1=").append(x1)
		.append(", x2=").append(x2)
		.append("}");
		return sb.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, a, b, c);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Quadratic quadratic = (Quadratic) o;
		return Objects.equals(id, quadratic.id) &&
				Objects.equals(a, quadratic.a) &&
				Objects.equals(b, quadratic.b) &&
				Objects.equals(c, quadratic.c);
	}

	private double getDiscriminant() {
		return a == null || b == null || c == null ? -1 : Math.pow(b, 2) - 4 * a * c;
	}

	public Double getX1() {
	    if (x1 == null)
            processResult();
		return x1;
	}

	public Double getX2() {
	    if (x2 == null)
            processResult();
		return x2;
	}

	private void processResult() {
        if (getDiscriminant() >= 0) {
            x1 = (-b - Math.sqrt(getDiscriminant())) / (2 * a);
	        x2 = (-b + Math.sqrt(getDiscriminant())) / (2 * a);
        }
    }

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getA() {
		return a;
	}

	public void setA(Double a) {
		this.a = a;
	}

	public Double getB() {
		return b;
	}

	public void setB(Double b) {
		this.b = b;
	}

	public Double getC() {
		return c;
	}

	public void setC(Double c) {
		this.c = c;
	}

	public void setX1(Double x1) {
		this.x1 = x1;
	}

	public void setX2(Double x2) {
		this.x2 = x2;
	}
}