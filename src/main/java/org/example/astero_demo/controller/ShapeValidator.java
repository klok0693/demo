package org.example.astero_demo.controller;

import org.apache.commons.lang3.StringUtils;
import org.example.astero_demo.adapter.model.ShapeParam;
import org.example.astero_demo.util.MathUtils;

import static java.lang.Double.parseDouble;
import static org.example.astero_demo.util.MathUtils.isNotNegative;

public enum ShapeValidator {
    INSTANCE;

    public boolean isValid(final ShapeParam param, final String value) {
        return switch (param) {
            case X, Y, WIDTH, HEIGHT, LAYER -> isNotNegative(parseDouble(value)); // TODO: max value check;
            case COLOR -> StringUtils.isNoneBlank(value);
            case ID -> false;
        };
    }
}
