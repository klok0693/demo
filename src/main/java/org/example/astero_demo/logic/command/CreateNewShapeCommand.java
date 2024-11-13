package org.example.astero_demo.logic.command;

import org.example.astero_demo.adapter.model.ModelAdapter;
import org.example.astero_demo.adapter.model.ShapeType;
import org.example.astero_demo.adapter.ui.RootAdapter;
import org.example.astero_demo.controller.ModelController;
import org.example.astero_demo.controller.ViewController;

public class CreateNewShapeCommand extends Command {
    private final ViewController viewController;
    private final ModelController modelController;

    private final int priority;
    private final double x, y;
    private final ShapeType type;

    private int createdShapeId;

    public CreateNewShapeCommand(
            final ViewController viewController,
            final ModelController modelController,
            final int priority,
            final double x,
            final double y,
            final ShapeType type) {
        this.viewController = viewController;
        this.modelController = modelController;
        this.priority = priority;
        this.x = x;
        this.y = y;
        this.type = type;
    }

    @Override
    public void doCommand() {
        this.createdShapeId = modelController.saveShape(priority, x, y, type);
        viewController.update();
    }

    @Override
    public void undoCommand() {
        modelController.removeShape(createdShapeId);
        viewController.update();
    }
}
