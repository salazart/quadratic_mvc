package com.sz.quadratic.exceptions;

public class QuadraticException extends Exception {
	  private static final long serialVersionUID = 1L;
	  public QuadraticException() { super(); }
	  public QuadraticException(String message) { super(message); }
	  public QuadraticException(String message, Throwable cause) { super(message, cause); }
	  public QuadraticException(Throwable cause) { super(cause); }
}
