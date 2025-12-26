package org.example.demo.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

/**
 * Model entity, representing a specific shape in 2D space
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
@Getter
@Setter
@EqualsAndHashCode
public abstract sealed class Shape implements Comparable<Shape>
        permits Rectangle, Ellipse {
    public static final String DEFAULT_COLOR_STRING = "-16744448";
    private static final int RANDOM_RANGE = 10_000;

    private int id;
    protected String priority;
    protected String x;
    protected String y;
    protected String width;
    protected String height;
    protected String color;

    protected Shape(
            final int id,
            final String priority,
            final String x,
            final String y,
            final String width,
            final String height) {
        this(id, priority, x, y, width, height, null);
    }

    protected Shape(
            final int id,
            final String priority,
            final String x,
            final String y,
            final String width,
            final String height,
            final String color) {
        this.id = id;
        this.priority = priority;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = StringUtils.isNotBlank(color) ? color : DEFAULT_COLOR_STRING;
    }

    /**
     * Is given point in bounds
     */
    public boolean isInBounds(final double pointX, final double pointY) {
        final double shapeX = parseDouble(this.x);
        final double shapeY = parseDouble(this.y);
        final double shapeWidth = parseDouble(this.width);
        final double shapeHeight = parseDouble(this.height);
        return pointX >= shapeX && pointX <= (shapeX + shapeWidth)
                && pointY >= shapeY && pointY <= (shapeY + shapeHeight);
    }

    /**
     * Visible part of the shape can be different from its bounds.<p>
     * Method checks, is given point in visible bounds of the shape
     */
    public boolean isInVisibleBounds(final double x, final double y) {
        final double shapeX = parseDouble(this.x);
        final double shapeY = parseDouble(this.y);
        final double shapeWidth = parseDouble(this.width);
        final double shapeHeight = parseDouble(this.height);
        return isInVisibleBounds(x, y, shapeX, shapeY, shapeWidth, shapeHeight);
    }

    protected abstract boolean isInVisibleBounds(
            double pointX,
            double pointY,
            double shapeX,
            double shapeY,
            double shapeWidth,
            double shapeHeight);

    public abstract ShapeType getType();

    public boolean isTheSame(final int id) {
        return this.id == id;
    }

    @Override
    public int compareTo(final Shape o) {
        return Integer.compare(parseInt(priority), parseInt(o.priority));
    }

}