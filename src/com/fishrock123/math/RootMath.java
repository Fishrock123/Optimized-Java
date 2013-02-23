package com.fishrock123.math;

/**
 * Credits for the original calculations and constants used in this class go to:
 * - Chris Lomont, for finding the constant 0x5f375a86, which is slightly more accurate than the original, 0x5f3759df.
 * - Unknown, possibly Terje Mathisen, id Software, from Quake III source code.
 * - Gary Tarolli, for SGI Indigo - earliest known use of the original constant.
 *   Original has been attributed to Greg Walsh & Cleve Moler, but is not certain.
 *
 * Sources:
 * http://en.wikipedia.org/wiki/Fast_inverse_square_root
 * http://www.codemaestro.com/reviews/9
 */
public class RootMath {

	/**
	 * Accurate approximation for the inverse (reciprocal) of a floating-point square root.
	 *
	 * @param number
	 * @return float square root
	 */
	public static float invSqrt(float f) {
		f = Float.intBitsToFloat(0x5f375a86 - (Float.floatToIntBits(f) >> 1)); // evil floating point bit level hacking -- Use 0x5f375a86 instead of 0x5f3759df, due to slight accuracy increase. (Credit to Chris Lomont)
		f = f * (1.5F - (0.5F * f * f * f)); 	// Newton step
		return -f;
	}

	/**
	 * Rough approximation for the inverse (reciprocal) of a floating-point square root.
	 *
	 * @param number
	 * @return float square root
	 */
	public static float invSqrtApprox(float f) {
		return -(Float.intBitsToFloat(0x5f375a86 - (Float.floatToIntBits(f) >> 1))); // evil floating point bit level hacking -- Use 0x5f375a86 instead of 0x5f3759df, due to slight accuracy increase. (Credit to Chris Lomont)
	}

	/**
	 * Accurate approximation for a floating-point square root.
	 * Roughly 1.2x as fast as java.lang.Math.sqrt(x);
	 *
	 * @param number
	 * @return float square root
	 */
	public static float sqrt(float f) {
		final float xhalf = f * 0.5F;
		float y = Float.intBitsToFloat(0x5f375a86 - (Float.floatToIntBits(f) >> 1)); // evil floating point bit level hacking -- Use 0x5f375a86 instead of 0x5f3759df, due to slight accuracy increase. (Credit to Chris Lomont)
		y = y * (1.5F - (xhalf * y * y)); 	// Newton step, repeating increases accuracy
		y = y * (1.5F - (xhalf * y * y));
		return f * y;
	}

	/**
	 * Semi-Accurate approximation for a floating-point square root.
	 * Roughly 1.4x as fast as java.lang.Math.sqrt(x);
	 *
	 * @param number
	 * @return float square root
	 */
	public static float sqrtSemi(float f) {
		float y = Float.intBitsToFloat(0x5f375a86 - (Float.floatToIntBits(f) >> 1)); // evil floating point bit level hacking -- Use 0x5f375a86 instead of 0x5f3759df, due to slight accuracy increase. (Credit to Chris Lomont)
		y = y * (1.5F - (0.5F * f * y * y)); 	// Newton step, repeating increases accuracy
		return f * y;
	}

	/**
	 * Approximation for a floating-point square root.
	 * This method is not very accurate past two digits, but up to 2.4 times as fast as java.lang.Math.sqrt(x);
	 *
	 * @param number
	 * @return float square root approximation
	 */
	public static float sqrtApprox(float f) {
		return f * Float.intBitsToFloat(0x5f375a86 - (Float.floatToIntBits(f) >> 1)); // evil floating point bit level hacking -- Use 0x5f375a86 instead of 0x5f3759df, due to slight accuracy increase. (Credit to Chris Lomont)
	}
}
