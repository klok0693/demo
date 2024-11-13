package org.example.astero_demo.adapter.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
public abstract class Shape {
    @Getter
    private final int id;
    @Getter @Setter
    private int priority;
    @Getter @Setter
    private double x, y, width, height;
    @Getter @Setter
    private int color;

    protected Shape(final int priority, final double x, final double y) {
        this(priority, x, y, 100, 100);
    }

    protected Shape(final int priority, final double x, final double y, final double width, final double height) {
        id = (int) (Math.random() * 10_000);
        this.priority = priority;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
