package org.example.demo.core.logic;

import org.example.demo.model.entity.Shape;

public interface ClipboardProcessor {

    void copy(Shape shape);

    void cut(int id);

    void paste(double x, double y);
}
