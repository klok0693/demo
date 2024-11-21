package org.example.astero_demo.adapter.model.state;

import org.example.astero_demo.adapter.model.entity.Shape;

public interface MutableModelState extends ModelState {

    void saveShape(Shape shape);

    void removeShape(int id);
}
