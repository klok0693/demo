package org.example.astero_demo.core.logic;

import org.example.astero_demo.core.model.entity.ShapeType;
import org.example.astero_demo.core.model.metadata.ShapeParam;

import java.util.Arrays;
import java.util.Objects;

import static org.example.astero_demo.core.util.MathUtils.*;

/**
 * Fail-safe validator for a shape parameters
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public class ShapeValidator {

    public boolean isValid(final ShapeParam param, final String value) {
        if (value == null) {
            return false;
        }
        return switch (param) {
            case ID -> isNonNegativeInteger(value);
            case X, Y, COLOR -> isNumber(value);
            case WIDTH, HEIGHT, PRIORITY -> isNonNegative(value);
            case TYPE -> Arrays.stream(ShapeType.values())
                    .map(Objects::toString)
                    .anyMatch(type -> type.equals(value));

            case null -> throw new UnsupportedOperationException("Shape param is null");
        };
    }
}
