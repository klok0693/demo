package org.example.astero_demo.controller;

import lombok.Setter;
import org.example.astero_demo.adapter.model.ModelAdapter;
import org.example.astero_demo.adapter.model.Shape;
import org.example.astero_demo.adapter.model.ShapeType;
import org.example.astero_demo.adapter.ui.RootAdapter;
import org.example.astero_demo.logic.command.CommandFactory;
import org.example.astero_demo.logic.command.CommandProcessor;

public class ModelController extends AbstractController {
    @Setter
    private ModelAdapter modelAdapter;

    public ModelController(CommandFactory commandFactory, CommandProcessor commandProcessor) {
        super(commandFactory, commandProcessor);
    }

    public int saveShape(final int priority, final double x, final double y, final ShapeType type) {
        return modelAdapter.saveShape(priority, x, y, type);
    }

    public void removeShape(final int id) {
        modelAdapter.removeShape(id);
    }

    @Override
    public void update() {}
}
