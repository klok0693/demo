package org.example.astero_demo.adapter.model;

public enum ShapeFactory {
    INSTANCE;

    Shape createShape(
            final String priority,
            final String x,
            final String y,
            final String width,
            final String height,
            final ShapeType type) {

        return switch (type) {
            case RECT -> new Rectangle(priority, x, y, width, height);
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }
}
