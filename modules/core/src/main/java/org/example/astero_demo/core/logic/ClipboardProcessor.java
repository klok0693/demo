package org.example.astero_demo.core.logic;

import org.example.astero_demo.model.entity.Shape;

public interface ClipboardProcessor {

    void copy(Shape shape);

    void cut(int id);

    void paste(double x, double y);
}
