package com.sz.quadratic.services;

public class DecimalService {
	public static boolean isDecimal(String value) {
		return value.matches("-?\\d+(\\.\\d+)?");
	}
}
