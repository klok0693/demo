package org.example.astero_demo.logic.command;

import org.example.astero_demo.adapter.model.Shape;
import org.example.astero_demo.controller.ModelController;
import org.example.astero_demo.controller.ViewController;

public class RemoveShapeCommand extends Command {
    private final ModelController modelController;
    private final ViewController viewController;
    private int shapeId;

    private Shape removedShape;

    public RemoveShapeCommand(
            final ModelController modelController,
            final ViewController viewController,
            final int shapeId) {
        this.modelController = modelController;
        this.viewController = viewController;
        this.shapeId = shapeId;
    }

    @Override
    public void doCommand() {
        this.removedShape = modelController.removeShape(shapeId);
        viewController.onRemoveUpdate();
    }

    @Override
    public void undoCommand() {
        this.shapeId = modelController.saveShape(
                removedShape.getPriority(),
                removedShape.getX(),
                removedShape.getY(),
                removedShape.getType());

        viewController.onCreateUpdate(removedShape.getX(), removedShape.getY());
    }
}
