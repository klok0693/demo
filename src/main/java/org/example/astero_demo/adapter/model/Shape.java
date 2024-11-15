package org.example.astero_demo.adapter.model;

import javafx.scene.paint.Color;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.example.astero_demo.util.ColorUtils;

@EqualsAndHashCode
public abstract class Shape {
    @Getter
    private int id;
    @Getter @Setter
    private String priority;
    @Getter @Setter
    private String x, y, width, height;
    @Getter @Setter
    private String color;

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

    public abstract ShapeType getType();
}