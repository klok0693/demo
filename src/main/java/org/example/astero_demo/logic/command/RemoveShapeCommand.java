package org.example.astero_demo.logic.command;

import org.example.astero_demo.adapter.model.entity.Shape;
import org.example.astero_demo.controller.model.ModelAdapterController;
import org.example.astero_demo.controller.model.ModelController;
import org.example.astero_demo.controller.ui.UIController;

import static java.lang.String.valueOf;

public class RemoveShapeCommand extends Command {
    private final UIController viewController;
    private final ModelController modelController;
    private int shapeId;

    private Shape removedShape;

    public RemoveShapeCommand(
            final UIController viewController,
            final ModelController modelController,
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
                removedShape.getId(),
                removedShape.getPriority(),
                removedShape.getX(),
                removedShape.getY(),
                removedShape.getWidth(),
                removedShape.getHeight(),
                removedShape.getColor(),
                removedShape.getType());

        //viewController.onCreateUpdate(removedShape.getX(), removedShape.getY());
        viewController.onCreateUpdate(removedShape.getId());
    }
}
