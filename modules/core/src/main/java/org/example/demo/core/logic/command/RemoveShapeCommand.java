package org.example.demo.core.logic.command;

import lombok.extern.slf4j.Slf4j;
import org.example.demo.model.entity.Shape;
import org.example.demo.core.controller.model.ModelController;
import org.example.demo.core.controller.ui.UIController;

import static org.example.demo.util.logging.MarkerStorage.COMMAND_MARKER;

/**
 * Represents a command to remove a shape.
 */
@Slf4j
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
        log.debug(COMMAND_MARKER, "Remove shape {}", shapeId);
        this.removedShape = modelController.removeShape(shapeId);
        log.debug("Update ui");
        viewController.onRemoveUpdate();
    }

    @Override
    public void undoCommand() {
        log.debug(COMMAND_MARKER, "Undo removing of the shape {}", removedShape);
        this.shapeId = modelController.saveShape(
                removedShape.getId(),
                removedShape.getPriority(),
                removedShape.getX(),
                removedShape.getY(),
                removedShape.getWidth(),
                removedShape.getHeight(),
                removedShape.getColor(),
                removedShape.getType());
        log.debug(COMMAND_MARKER, "Update ui for {}", shapeId);
        viewController.onCreateUpdate(removedShape.getId());
    }
}
