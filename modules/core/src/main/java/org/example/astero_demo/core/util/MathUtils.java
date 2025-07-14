package org.example.astero_demo.core.util;

import lombok.experimental.UtilityClass;

import java.util.regex.Pattern;

import static java.lang.Double.parseDouble;

/**
 * @author Pilip Yurchanka
 * @since v1.0
 */
@UtilityClass
public class MathUtils {
    private static final Pattern NON_NEGATIVE_INTEGER_PATTERN = Pattern.compile("^\\d+$");
    private static final Pattern NUMBER_PATTERN = Pattern.compile("-?\\d+(\\.\\d+)?");

    /**
     *
     * @param value
     * @return
     */
    public static boolean isNumber(final String value) {
        return NUMBER_PATTERN.matcher(value).matches();
    }

    public static boolean isNonNegative(final String value) {
        return isNumber(value) && parseDouble(value) >= 0.0;
    }

    public static boolean isNonNegativeInteger(final String input) {
        return NON_NEGATIVE_INTEGER_PATTERN.matcher(input).matches();
    }
}
