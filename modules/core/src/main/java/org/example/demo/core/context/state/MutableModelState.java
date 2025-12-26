package org.example.demo.core.context.state;

import org.example.demo.model.entity.Shape;

public interface MutableModelState extends ModelState {

    void saveShape(Shape shape);

    void removeShape(int id);
}
