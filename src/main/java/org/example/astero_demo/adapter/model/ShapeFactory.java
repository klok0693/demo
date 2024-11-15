package org.example.astero_demo.adapter.model;

public enum ShapeFactory {
    INSTANCE;

    Shape createShape(
            final Integer id,
            final String priority,
            final String x,
            final String y,
            final String width,
            final String height,
            final ShapeType type) {

        return switch (type) {
            case RECT -> {
                if (id != null) {
                    yield new Rectangle(id, priority, x, y, width, height);
                }
                else {
                    yield new Rectangle(priority, x, y, width, height);
                }
            }
            case OVAL -> {
                if (id != null) {
                    yield new Oval(id, priority, x, y, width, height);
                }
                else {
                    yield new Oval(priority, x, y, width, height);
                }
            }
        };
    }
}
