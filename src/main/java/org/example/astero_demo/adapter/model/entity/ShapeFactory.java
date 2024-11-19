package org.example.astero_demo.adapter.model.entity;

public enum ShapeFactory {
    INSTANCE;

    public Shape createShape(
            final Integer id,
            final String priority,
            final String x,
            final String y,
            final String width,
            final String height,
            final String color,
            final ShapeType type) {

        return switch (type) {
            case RECT -> {
                if (id != null) {
                    yield new Rectangle(id, priority, x, y, width, height, color);
                }
                else {
                    yield new Rectangle(priority, x, y, width, height, color);
                }
            }
            case ELLIPSE -> {
                if (id != null) {
                    yield new Ellipse(id, priority, x, y, width, height, color);
                }
                else {
                    yield new Ellipse(priority, x, y, width, height, color);
                }
            }
        };
    }
}
