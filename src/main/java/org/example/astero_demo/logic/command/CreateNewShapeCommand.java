package org.example.astero_demo.logic.command;

import org.example.astero_demo.adapter.model.ModelAdapter;
import org.example.astero_demo.adapter.model.ShapeType;
import org.example.astero_demo.adapter.ui.RootAdapter;
import org.example.astero_demo.controller.ModelController;
import org.example.astero_demo.controller.ViewController;

public class CreateNewShapeCommand extends Command {
    private final ViewController viewController;
    private final ModelController modelController;

    private final String priority;
    private final String x, y, width, height;
    private final ShapeType type;

    private int createdShapeId;

    public CreateNewShapeCommand(
            final ViewController viewController,
            final ModelController modelController,
            final String priority,
            final String x,
            final String y,
            final String width,
            final String height,
            final ShapeType type) {
        this.viewController = viewController;
        this.modelController = modelController;
        this.priority = priority;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.type = type;
    }

    @Override
    public void doCommand() {
        this.createdShapeId = modelController.saveShape(priority, x, y, width, height, type);
        viewController.onCreateUpdate(x, y);
    }

    @Override
    public void undoCommand() {
        modelController.removeShape(createdShapeId);
        viewController.onRemoveUpdate();
    }
}
