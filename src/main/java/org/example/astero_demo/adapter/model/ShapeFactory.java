package org.example.astero_demo.adapter.model;

public enum ShapeFactory {
    INSTANCE;

    Shape createShape(
            final int priority,
            final double x,
            final double y,
            final ShapeType type) {

        return switch (type) {
            case RECT -> new Rectangle(priority, x, y);
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }
}
