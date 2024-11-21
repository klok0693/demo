package org.example.astero_demo.logic.command;

import lombok.extern.slf4j.Slf4j;
import org.example.astero_demo.adapter.model.metadata.ParamInfo;
import org.example.astero_demo.adapter.model.metadata.ShapeParam;
import org.example.astero_demo.adapter.model.entity.ShapeType;
import org.example.astero_demo.controller.model.ModelAdapterController;
import org.example.astero_demo.controller.model.ModelController;
import org.example.astero_demo.controller.ui.UIController;

import static java.lang.Integer.parseInt;
import static org.example.astero_demo.realization.logging.MarkerStorage.COMMAND_MARKER;
import static org.example.astero_demo.util.ParamUtils.getParamInfo;

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
            final ParamInfo... infos) {
        super(infos);
        this.viewController = viewController;
        this.modelController = modelController;
    }

    @Override
    public void doCommand() {
        log.debug(COMMAND_MARKER, "Create new shape with params {}", paramInfos);
        this.createdShapeId = modelController.saveShape(
                getParamInfo(paramInfos, ShapeParam.PRIORITY),
                getParamInfo(paramInfos, ShapeParam.X),
                getParamInfo(paramInfos, ShapeParam.Y),
                getParamInfo(paramInfos, ShapeParam.WIDTH),
                getParamInfo(paramInfos, ShapeParam.HEIGHT),
                getParamInfo(paramInfos, ShapeParam.COLOR),
                ShapeType.valueOf(getParamInfo(paramInfos, ShapeParam.TYPE)));
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
