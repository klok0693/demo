package org.example.demo.model.entity;

/**
 * @author Pilip Yurchanka
 * @since v1.0
 */
public non-sealed class Ellipse extends Shape {

    public Ellipse(
            final int id,
            final String priority,
            final String x,
            final String y,
            final String width,
            final String height,
            final String color) {
        super(id, priority, x, y, width, height, color);
    }

    public Ellipse(
            final int id,
            final String priority,
            final String x,
            final String y,
            final String width,
            final String height) {
        super(id, priority, x, y, width, height);
    }

    @Override
    protected boolean isInVisibleBounds(
            final double pointX,
            final double pointY,
            final double shapeX,
            final double shapeY,
            final double shapeWidth,
            final double shapeHeight) {

        final double centerX = shapeX + shapeWidth / 2.0; // Center X
        final double centerY = shapeY + shapeHeight / 2.0; // Center Y
        final double semiWidth = shapeWidth / 2.0; // Semi-major axis (horizontal)
        final double semiHeight = shapeHeight / 2.0; // Semi-minor axis (vertical)

        final double dx = pointX - centerX; // Distance from point to center (x-axis)
        final double dy = pointY - centerY; // Distance from point to center (y-axis)

        // Equation of an ellipse
        return (dx * dx) / (semiWidth * semiWidth) + (dy * dy) / (semiHeight * semiHeight) <= 1.0;
    }

    @Override
    public ShapeType getType() {
        return ShapeType.ELLIPSE;
    }
}
