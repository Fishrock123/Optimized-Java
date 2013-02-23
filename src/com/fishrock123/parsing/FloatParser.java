package com.fishrock123.parsing;

/**
 * @author Gaz Davidson
 * Source: http://bitplane.net/2010/08/java-float-fast-parser/
 */
public class FloatParser {

	/**
	 * Parses a float from a string something like 6 times as fast as Float.parseFloat(String).
	 * 
	 * @param string
	 * @return float number
	 */
	public static float parse(String f) {
		final int len   = f.length();
		float     ret   = 0f;         // return value
		int       pos   = 0;          // read pointer position
		int       part  = 0;          // the current part (int, float and sci parts of the number)
		boolean   neg   = false;      // true if part is a negative number

		// find start
		while (pos < len && (f.charAt(pos) < '0' || f.charAt(pos) > '9') && f.charAt(pos) != '-' && f.charAt(pos) != '.') {
			pos++;
		}

		// sign
		if (f.charAt(pos) == '-') {
			neg = true;
			pos++;
		}

		// integer part
		while (pos < len && !(f.charAt(pos) > '9' || f.charAt(pos) < '0')) {
			part = part * 10 + (f.charAt(pos++) - '0');
		}
		ret = neg ? (float)(part * -1) : (float)part;

		// float part
		if (pos < len && f.charAt(pos) == '.') {
			pos++;
			int mul = 1;
			part = 0;
			while (pos < len && !(f.charAt(pos) > '9' || f.charAt(pos) < '0')) {
				part = part * 10 + (f.charAt(pos) - '0');
				mul *= 10; pos++;
			}
			ret = neg ? ret - (float)part / (float)mul : ret + (float)part / (float)mul;
		}
		return ret;
	}

	/**
	 * Parses a float from a string something like 6 times as fast as Float.parseFloat(String).
	 * Also parses floats in scientific notation from string.
	 * 
	 * @param string
	 * @return float number
	 */
	public static float parseScientific(String f) {
		final int len   = f.length();
		float     ret   = 0f;         // return value
		int       pos   = 0;          // read pointer position
		int       part  = 0;          // the current part (int, float and sci parts of the number)
		boolean   neg   = false;      // true if part is a negative number

		// find start
		while (pos < len && (f.charAt(pos) < '0' || f.charAt(pos) > '9') && f.charAt(pos) != '-' && f.charAt(pos) != '.') {
			pos++;
		}

		// sign
		if (f.charAt(pos) == '-') {
			neg = true;
			pos++;
		}

		// integer part
		while (pos < len && !(f.charAt(pos) > '9' || f.charAt(pos) < '0')) {
			part = part * 10 + (f.charAt(pos++) - '0');
		}
		ret = neg ? (float)(part * -1) : (float)part;

		// float part
		if (pos < len && f.charAt(pos) == '.') {
			pos++;
			int mul = 1;
			part = 0;
			while (pos < len && !(f.charAt(pos) > '9' || f.charAt(pos) < '0')) {
				part = part * 10 + (f.charAt(pos) - '0');
				mul *= 10; pos++;
			}
			ret = neg ? ret - (float)part / (float)mul : ret + (float)part / (float)mul;
		}

		// scientific part
		if (pos < len && (f.charAt(pos) == 'e' || f.charAt(pos) == 'E')) {
			pos++;
			neg = (f.charAt(pos) == '-'); pos++;
			part = 0;
			while (pos < len && !(f.charAt(pos) > '9' || f.charAt(pos) < '0')) {
				part = part * 10 + (f.charAt(pos++) - '0');
			}
			if (neg) {
				ret = ret / (float)Math.pow(10, part);
			} else {
				ret = ret * (float)Math.pow(10, part);
			}
		}
		return ret;
	}
}
