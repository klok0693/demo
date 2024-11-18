package org.example.astero_demo.util;

import lombok.experimental.UtilityClass;

import java.util.regex.Pattern;

@UtilityClass
public class MathUtils {

    //private static final Pattern PATTERN = Pattern.compile("-?\\d+(\\.\\d+)?");

    public static boolean isNonNegative(final Number value) {
        return value.doubleValue() >= 0.0;
    }

    public static boolean isNonNegativeInteger(final String input) {
        final String regex = "^\\d+$";
        return Pattern.matches(regex, input);
    }
}
