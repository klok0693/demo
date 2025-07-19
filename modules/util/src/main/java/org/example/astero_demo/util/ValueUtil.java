package org.example.astero_demo.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

@UtilityClass
public class ValueUtil {
    private static final String NULL = "NULL";

    public static boolean containValue(final String input) {
        return StringUtils.isNotBlank(input) && !input.equalsIgnoreCase(NULL);
    }
}
