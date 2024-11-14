package org.example.astero_demo.adapter.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
public abstract class Shape {
    @Getter
    private final int id;
    @Getter @Setter
    private String priority;
    @Getter @Setter
    private String x, y, width, height;
    @Getter @Setter
    private String color;

    protected Shape(final String priority, final String x, final String y) {
        this(priority, x, y, "100", "100");
    }

    protected Shape(final String priority, final String x, final String y, final String width, final String height) {
        id = (int) (Math.random() * 10_000);
        this.priority = priority;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract ShapeType getType();
}
