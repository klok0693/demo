package org.example.astero_demo.adapter.model.entity;

import javafx.scene.paint.Color;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.example.astero_demo.util.ColorUtils;

import static java.lang.Double.parseDouble;
import static java.lang.Double.valueOf;
import static java.lang.Integer.parseInt;

@EqualsAndHashCode
public abstract class Shape implements Comparable<Shape> {
    @Getter
    private int id;
    @Getter @Setter
    protected String priority;
    @Getter @Setter
    protected String x, y, width, height;
    @Getter @Setter
    protected String color;

    protected Shape(
            final String priority,
            final String x,
            final String y,
            final String width,
            final String height) {

        this((int) (Math.random() * 10_000), priority, x, y, width, height, null);
    }

    protected Shape(
            final String priority,
            final String x,
            final String y,
            final String width,
            final String height,
            final String color) {

        this((int) (Math.random() * 10_000), priority, x, y, width, height, color);
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
        this.color = StringUtils.isNotBlank(color) ? color : String.valueOf(ColorUtils.convert(Color.GREEN));
    }

    public boolean isInBounds(final double pointX, final double pointY) {
        final double shapeX = parseDouble(this.x);
        final double shapeY = parseDouble(this.y);
        final double shapeWidth = parseDouble(this.width);
        final double shapeHeight = parseDouble(this.height);
        return pointX >= shapeX && pointX <= (shapeX + shapeWidth)
                && pointY >= shapeY && pointY <= (shapeY + shapeHeight);
    }

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

    @Override
    public int compareTo(final Shape o) {
        return Integer.compare(parseInt(priority), parseInt(o.priority));
    }

}