package org.example.astero_demo.util;

import lombok.experimental.UtilityClass;

import java.util.regex.Pattern;

/**
 * @author Pilip Yurchanka
 * @since v1.0
 */
@UtilityClass
public class MathUtils {
    private static final Pattern NON_NEGATIVE_INTEGER_PATTERN = Pattern.compile("^\\d+$");

    public static boolean isNonNegative(final Number value) {
        return value.doubleValue() >= 0.0;
    }

    public static boolean isNonNegativeInteger(final String input) {
        return NON_NEGATIVE_INTEGER_PATTERN.matcher(input).matches();
    }
}
