package org.example.astero_demo.util;

import lombok.experimental.UtilityClass;

import java.util.regex.Pattern;

@UtilityClass
public class MathUtils {

    private static final Pattern PATTERN = Pattern.compile("-?\\d+(\\.\\d+)?");

    public static boolean isDouble(final String str) {
        return PATTERN.matcher(str).matches(); // Matches double format with optional decimal point
    }

    public static boolean isNotNegative(final Number value) {
        return value.doubleValue() >= 0.0;
    }
}
