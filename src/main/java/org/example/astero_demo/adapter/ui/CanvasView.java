package org.example.astero_demo.adapter.ui;

import org.example.astero_demo.adapter.model.entity.Shape;

public interface CanvasView {

    Shape selectElement(int id);

    Shape selectElement(double mouseX, double mouseY);

    void update();
}
