package org.example.astero_demo.model.state;

import org.example.astero_demo.model.entity.Shape;

public interface MutableModelState extends ModelState {

    void saveShape(Shape shape);

    void removeShape(int id);
}
