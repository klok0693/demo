package org.example.demo.core.logic.command;

import lombok.extern.slf4j.Slf4j;
import org.example.demo.core.controller.model.ModelController;
import org.example.demo.core.controller.ui.UIController;
import org.example.demo.model.metadata.dto.ShapeParams;

import static org.example.demo.util.logging.MarkerStorage.COMMAND_MARKER;

/**
 * Represents a command to modify the parameters of a shape
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
@Slf4j
public class ModifyShapeCommand extends ParamCommand {
    private final UIController viewController;
    private final ModelController modelController;

    protected final int modifyShapeId;

    public ModifyShapeCommand(
            final UIController viewController,
            final ModelController modelController,
            final int modifyShapeId,
            final ShapeParams shapeParams) {
        super(shapeParams);
        this.viewController = viewController;
        this.modelController = modelController;
        this.modifyShapeId = modifyShapeId;
    }

    @Override
    public void doCommand() {
        log.debug(COMMAND_MARKER, "Update existed shape {} with new params {}", modifyShapeId, shapeParams);
        shapeParams.forEach((paramType, paramInfo) -> {
            final String oldValue = modelController.getShapeParam(modifyShapeId, paramType);
            shapeParams.setOldValue(paramType, oldValue);
            modelController.modifyShapeParam(modifyShapeId, paramType, paramInfo.getNewValue());
        });

        log.debug(COMMAND_MARKER, "Update ui for {}", modifyShapeId);
        viewController.onModifyUpdate(modifyShapeId);
    }

    @Override
    public void undoCommand() {
        log.debug(COMMAND_MARKER, "Undo shape {} modification", modifyShapeId);
        shapeParams.forEach((paramType, paramInfo) -> {
            modelController.modifyShapeParam(modifyShapeId, paramType, paramInfo.getOldValue());
        });

        log.debug(COMMAND_MARKER, "Update ui for {}", modifyShapeId);
        viewController.onModifyUpdate(modifyShapeId);
    }
}
