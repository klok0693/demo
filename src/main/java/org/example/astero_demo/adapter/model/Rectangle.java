package org.example.astero_demo.adapter.model;

public class Rectangle extends Shape {

    protected Rectangle(
            final int id,
            final String priority,
            final String x,
            final String y,
            final String width,
            final String height,
            final String color) {
        super(id, priority, x, y, width, height, color);
    }

    protected Rectangle(
            final String priority,
            final String x,
            final String y,
            final String width,
            final String height,
            final String color) {
        super(priority, x, y, width, height, color);
    }

    protected Rectangle(
            final String priority,
            final String x,
            final String y,
            final String width,
            final String height) {
        super(priority, x, y, width, height);
    }

    @Override
    public ShapeType getType() {
        return ShapeType.RECT;
    }
}
