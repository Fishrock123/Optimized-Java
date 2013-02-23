package com.fishrock123.parsing;

/**
 * @author Dr. David R. Nadeau
 * Source: http://nadeausoftware.com/articles/2009/08/java_tip_how_parse_integers_quickly
 */
public class IntParser {

	/**
	 * Parses an integer from a string at roughly 3 times the speed of Integer.parseInt(String).
	 * 
	 * @param string
	 * @return integer number
	 */
	public static int parse(final String s) {
		if (s == null) throw new NumberFormatException("Null string");

		// Check for a sign.
		int num  = 0;
		int sign = -1;
		final int len  = s.length();
		final char ch  = s.charAt(0);
		if (ch == '-') {
			if (len == 1) throw new NumberFormatException("Missing digits:  " + s);
			sign = 1;
		} else {
			final int d = ch - '0';
			if (d < 0 || d > 9) throw new NumberFormatException("Malformed: " + s);
			num = -d;
		}

		// Build the number.
		final int max = (sign == -1) ? -Integer.MAX_VALUE : Integer.MIN_VALUE;
		final int multmax = max / 10;
		int i = 1;
		while (i < len) {
			int d = s.charAt(i++) - '0';
			if (d < 0 || d > 9) throw new NumberFormatException("Malformed: " + s);
			if (num < multmax) throw new NumberFormatException("Over/underflow: " + s);
			num *= 10;
			if (num < (max + d)) throw new NumberFormatException("Over/underflow: " + s);
			num -= d;
		}
		return sign * num;
	}

	/**
	 * Parses an integer from a string at roughly 4 times the speed of Integer.parseInt(String), but is unsafe.
	 * 
	 * @param string
	 * @return integer number
	 */
	public static int parseUnsafe(final String s) {

		// Check for a sign.
		int num  = 0;
		int sign = -1;
		final int len  = s.length();
		final char ch  = s.charAt(0);
		if (ch == '-') {
			sign = 1;
		} else {
			num = '0' - ch;
		}

		// Build the number.
		int i = 1;
		while (i < len) {
			num = num * 10 + '0' - s.charAt(i++);
		}
		return sign * num;
	}
}
