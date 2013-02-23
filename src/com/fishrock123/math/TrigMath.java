package com.fishrock123.math;

/**
 * Credits for atan2 and it's sub-methods goes to user aioobe on stackoverflow.com
 * Source: http://stackoverflow.com/questions/4454630/j2me-calculate-the-the-distance-between-2-latitude-and-longitude
 */
public class TrigMath {

	static final double sq2p1 = 2.414213562373095048802e0;
	static final double sq2m1 = .414213562373095048802e0;
	static final double p4 = .161536412982230228262e2;
	static final double p3 = .26842548195503973794141e3;
	static final double p2 = .11530293515404850115428136e4;
	static final double p1 = .178040631643319697105464587e4;
	static final double p0 = .89678597403663861959987488e3;
	static final double q4 = .5895697050844462222791e2;
	static final double q3 = .536265374031215315104235e3;
	static final double q2 = .16667838148816337184521798e4;
	static final double q1 = .207933497444540981287275926e4;
	static final double q0 = .89678597403663861962481162e3;

	/**
	 * Precalulated PI numbers.
	 * 
	 * PI divided by 2, 4, 180
	 * PI times 2, 4
	 * 180 divided by PI
	 */
	public static final double PIO2 = 1.5707963267948966135e0;
	public static final double PIO4 = 0.7853981633974483096e0;
	public static final double PIX2 = 6.2831853071795864766e0;
	public static final double PIX4 = 12.566370614359172953e0;
	public static final double PIO180 = 0.01745329251994329e0;
	public static final double PIU180 = 57.2957795130823208e0;

	/**
	 * Sine lookup table
	 */
	private static float[] sin = new float[65536];

	/**
	 * Lookup table initiation
	 */
	static {
		for (int i = 0; i < 65536; ++i) {
			sin[i] = (float) Math.sin(i * PIX2 / 65536.0D);
		}
	}

	private static double mxatan(double d) {
		final double asq = d * d;
		double value = ((((p4 * asq + p3) * asq + p2) * asq + p1) * asq + p0);
		value = value / (((((asq + q4) * asq + q3) * asq + q2) * asq + q1) * asq + q0);
		return value * d;
	}


	private static double msatan(double a) {
		return a < sq2m1 ? mxatan(a)
				: a > sq2p1 ? PIO2 - mxatan(1 / a)
						: PIO4 + mxatan((a - 1) / (a + 1));
	}

	public static double atan(double a) {
		return a > 0 ? msatan(a) : -msatan(-a);
	}

	/**
	 * Fast arctangent2 calculation
	 * 
	 * @param y
	 * @param x
	 * @return rotation from 0,0 in radians
	 */
	public static double atan2(double y, double x) {
		if (y + x == y)
			return y >= 0 ? PIO2 : -PIO2;
			y = atan(y / x);
			return x < 0 ? y <= 0 ? y + Math.PI : y - Math.PI : y;
	}

	/**
	 * Fast sin lookup
	 * 
	 * @param radians
	 * @return sine
	 */
	public static final float sin(float radians) {
		return sin[(int) (radians * 10430.378F) & '\uffff']; //Floating-point bit hax.
		//(rad * degrees to index) [bit conjuction] sine-mask
	}

	/**
	 * Fast cosine lookup
	 * 
	 * @param radians
	 * @return cosine
	 */
	public static final float cos(float radians) {
		return sin[(int) (radians * 10430.378F + 16384.0F) & '\uffff']; //Even more Floating-point bit hax.
		//(rad * degrees to index + cosine modifier) [bit conjuction] sine-mask
	}

	/**
	 * Fast conversion from radians to degrees
	 * 
	 * @param radians
	 * @return degrees
	 */
	public static final double toDegrees(double radians) {
		return radians * PIU180;
	}

	/**
	 * Fast conversion from degrees to radians
	 * 
	 * @param degrees
	 * @return radians
	 */
	public static final double toRadians(double degrees) {
		return degrees * PIO180;
	}
}