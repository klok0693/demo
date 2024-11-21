package org.example.astero_demo.controller;

import org.apache.commons.lang3.StringUtils;
import org.example.astero_demo.adapter.model.metadata.ShapeParam;
import org.example.astero_demo.adapter.model.entity.ShapeType;

import java.util.Arrays;
import java.util.Objects;

import static java.lang.Double.parseDouble;
import static org.example.astero_demo.util.MathUtils.isNonNegative;
import static org.example.astero_demo.util.MathUtils.isNonNegativeInteger;

/**
 * Validator for a shape parameters
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public enum ShapeValidator {
    INSTANCE;

    public static boolean isValid(final ShapeParam param, final String value) {
        return switch (param) {
            case ID -> isNonNegativeInteger(value);
            case X, Y -> true;
            case WIDTH, HEIGHT, PRIORITY -> isNonNegative(parseDouble(value));
            case COLOR -> StringUtils.isNoneBlank(value);
            case TYPE -> Arrays.stream(ShapeType.values())
                    .map(Objects::toString)
                    .anyMatch(type -> type.equals(value));
        };
    }
}
