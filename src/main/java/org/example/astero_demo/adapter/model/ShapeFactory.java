package org.example.astero_demo.adapter.model;

public enum ShapeFactory {
    INSTANCE;

    Shape createShape(
            final String priority,
            final String x,
            final String y,
            final ShapeType type) {

        return switch (type) {
            case RECT -> new Rectangle(priority, x, y);
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }
}
