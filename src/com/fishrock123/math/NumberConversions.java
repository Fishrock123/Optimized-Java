package com.fishrock123.math;

/**
 * This class was taken from the Bukkit project, and as such it is Licensed under GPLv3.
 * Source: https://github.com/Bukkit/Bukkit/blob/master/src/main/java/org/bukkit/util/NumberConversions.java
 */
public class NumberConversions {
	
	public static int floor(final double num) {
        final int floor = (int) num;
        return floor == num ? floor : floor - (int) (Double.doubleToRawLongBits(num) >>> 63);
    }

    public static int ceil(final double num) {
        final int floor = (int) num;
        return floor == num ? floor : floor + (int) (~Double.doubleToRawLongBits(num) >>> 63);
    }

    public static int round(final double num) {
        return floor(num + 0.5d);
    }
}
