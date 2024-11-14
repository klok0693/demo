package org.example.astero_demo.controller;

import org.example.astero_demo.adapter.model.ShapeParam;
import org.example.astero_demo.util.MathUtils;

import static org.example.astero_demo.util.MathUtils.isNotNegative;

public enum ShapeValidator {
    INSTANCE;

    public boolean isValid(final ShapeParam param, final Number value) {
        return switch (param) {
            case X, Y, WIDTH, HEIGHT, LAYER -> isNotNegative(value); // TODO: max value check;
            case COLOR, ID -> false;
        };
    }
}
