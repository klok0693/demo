package org.example.astero_demo.adapter.model.entity;

public class Oval extends Shape {

    public Oval(
            final int id,
            final String priority,
            final String x,
            final String y,
            final String width,
            final String height,
            final String color) {
        super(id, priority, x, y, width, height, color);
    }

    public Oval(
            final String priority,
            final String x,
            final String y,
            final String width,
            final String height,
            final String color) {
        super(priority, x, y, width, height, color);
    }

    public Oval(
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

        final double radiusX = shapeWidth / 2.0;
        final double radiusY = shapeHeight / 2.0;
        final double centerX = shapeX + radiusX; // Calculate center X
        final double centerY = shapeY + radiusY; // Calculate center Y
        final double dx = pointX - centerX; // Horizontal distance to the center
        final double dy = pointY - centerY; // Vertical distance to the center
        final double distanceSquared = dx * dx + dy * dy; // Squared distance to the center
        final double radiusSquared = radiusX * radiusY; // Squared radius

        return distanceSquared <= radiusSquared; // Inside or on the boundary
    }

    @Override
    public ShapeType getType() {
        return ShapeType.OVAL;
    }
}
