package org.example.astero_demo.controller;

import org.apache.commons.lang3.StringUtils;
import org.example.astero_demo.adapter.model.metadata.ShapeParam;
import org.example.astero_demo.adapter.model.entity.ShapeType;

import java.util.Arrays;
import java.util.Objects;

import static java.lang.Double.parseDouble;
import static org.example.astero_demo.util.MathUtils.isNotNegative;

public enum ShapeValidator {
    INSTANCE;

    public boolean isValid(final ShapeParam param, final String value) {
        return switch (param) {
            case X, Y, WIDTH, HEIGHT, PRIORITY -> isNotNegative(parseDouble(value)); // TODO: max value check;
            case COLOR -> StringUtils.isNoneBlank(value);
            case TYPE -> Arrays.stream(ShapeType.values())
                    .map(Objects::toString)
                    .anyMatch(type -> type.equals(value));
        };
    }
}
