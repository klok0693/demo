package org.example.astero_demo.core.logic.command;

import lombok.extern.slf4j.Slf4j;
import org.example.astero_demo.model.entity.ShapeType;
import org.example.astero_demo.model.metadata.ShapeParam;
import org.example.astero_demo.core.controller.model.ModelController;
import org.example.astero_demo.core.controller.ui.UIController;
import org.example.astero_demo.model.metadata.dto.ShapeParams;

import static org.example.astero_demo.util.logging.MarkerStorage.COMMAND_MARKER;

/**
 * A command implementation for creating a new shape with the provided parameters.
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
@Slf4j
public class CreateNewShapeCommand extends ParamCommand {
    private final UIController viewController;
    private final ModelController modelController;

    private int createdShapeId;

    public CreateNewShapeCommand(
            final UIController viewController,
            final ModelController modelController,
            final ShapeParams shapeParams) {
        super(shapeParams);
        this.viewController = viewController;
        this.modelController = modelController;
    }

    @Override
    public void doCommand() {
        log.debug(COMMAND_MARKER, "Create new shape with params {}", shapeParams);
        this.createdShapeId = modelController.saveShape(
                shapeParams.getNewValue(ShapeParam.PRIORITY),
                shapeParams.getNewValue(ShapeParam.X),
                shapeParams.getNewValue(ShapeParam.Y),
                shapeParams.getNewValue(ShapeParam.WIDTH),
                shapeParams.getNewValue(ShapeParam.HEIGHT),
                shapeParams.getNewValue(ShapeParam.COLOR),
                ShapeType.valueOf(shapeParams.getNewValue(ShapeParam.TYPE)));

        log.debug(COMMAND_MARKER, "Update ui for new shape {}", createdShapeId);
        viewController.onCreateUpdate(createdShapeId);
    }

    @Override
    public void undoCommand() {
        log.debug(COMMAND_MARKER, "Undo creation of the shape {}", createdShapeId);
        modelController.removeShape(createdShapeId);
        log.debug(COMMAND_MARKER, "Update ui");
        viewController.onRemoveUpdate();
    }
}
