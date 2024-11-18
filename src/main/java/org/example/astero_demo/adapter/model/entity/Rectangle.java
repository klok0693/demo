package org.example.astero_demo.adapter.model.entity;

import static java.lang.Double.parseDouble;

public class Rectangle extends Shape {

    public Rectangle(
            final int id,
            final String priority,
            final String x,
            final String y,
            final String width,
            final String height,
            final String color) {
        super(id, priority, x, y, width, height, color);
    }

    public Rectangle(
            final String priority,
            final String x,
            final String y,
            final String width,
            final String height,
            final String color) {
        super(priority, x, y, width, height, color);
    }

    public Rectangle(
            final String priority,
            final String x,
            final String y,
            final String width,
            final String height) {
        super(priority, x, y, width, height);
    }

    @Override
    protected boolean isInVisibleBounds(
            final double pointX,
            final double pointY,
            final double shapeX,
            final double shapeY,
            final double shapeWidth,
            final double shapeHeight) {
        return pointX >= shapeX && pointX <= (shapeX + shapeWidth)
                && pointY >= shapeY && pointY <= (shapeY + shapeHeight);
    }

    @Override
    public ShapeType getType() {
        return ShapeType.RECT;
    }
}
