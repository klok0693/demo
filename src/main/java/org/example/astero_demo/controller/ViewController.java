package org.example.astero_demo.controller;

import lombok.Setter;
import org.example.astero_demo.adapter.model.Shape;
import org.example.astero_demo.adapter.model.ModelAdapter;
import org.example.astero_demo.adapter.ui.RootAdapter;
import org.example.astero_demo.logic.event.ui.CreateNewShapeEvent;
import org.example.astero_demo.logic.event.ui.UIEvent;

public class ViewController {
    @Setter
    private RootAdapter rootAdapter;
    private final ModelAdapter modelAdapter;

    public ViewController(final ModelAdapter modelAdapter) {
        this.modelAdapter = modelAdapter;
    }

    public void process(UIEvent e) {
        if (e instanceof CreateNewShapeEvent) {
            CreateNewShapeEvent ev = (CreateNewShapeEvent) e;
            modelAdapter.saveShape(ev.getPriority(), ev.getX(), ev.getY(), ev.getType());
            rootAdapter.update();
        }
    }
}
