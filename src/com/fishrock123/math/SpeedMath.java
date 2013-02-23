package com.fishrock123.math;

/**
 * @author Fishrock123
 * 
 * This stuff is public domain
 */
public class SpeedMath {

	public static double abs(double d) {
		return d >= 0.0 ? d : -d;
	}

	public static double sq(double d) {
		return d * d;
	}

	public static double cube(double d) {
		return d * d * d;
	}

	public static double half(double d) {
		return d * .5;
	}
}
