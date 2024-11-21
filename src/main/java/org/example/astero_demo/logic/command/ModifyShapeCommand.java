package org.example.astero_demo.logic.command;

import lombok.extern.slf4j.Slf4j;
import org.example.astero_demo.adapter.model.metadata.ParamInfo;
import org.example.astero_demo.controller.model.ModelController;
import org.example.astero_demo.controller.ui.UIController;

import static org.example.astero_demo.realization.logging.MarkerStorage.COMMAND_MARKER;

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
            final ParamInfo... infos) {
        super(infos);
        this.viewController = viewController;
        this.modelController = modelController;
        this.modifyShapeId = modifyShapeId;
    }

    @Override
    public void doCommand() {
        log.debug(COMMAND_MARKER, "Update existed shape {} with new params {}", modifyShapeId, paramInfos);
        for (final ParamInfo info : paramInfos) {
            final String oldValue = modelController.getShapeParam(modifyShapeId, info.getParam());
            info.setOldValue(oldValue);

            modelController.modifyShapeParam(modifyShapeId, info.getParam(), info.getNewValue());
        }
        log.debug(COMMAND_MARKER, "Update ui for {}", modifyShapeId);
        viewController.onModifyUpdate(modifyShapeId);
    }

    @Override
    public void undoCommand() {
        log.debug(COMMAND_MARKER, "Undo shape {} modification", modifyShapeId);
        for (final ParamInfo info : paramInfos) {
            modelController.modifyShapeParam(modifyShapeId, info.getParam(), info.getOldValue());
        }
        log.debug(COMMAND_MARKER, "Update ui for {}", modifyShapeId);
        viewController.onModifyUpdate(modifyShapeId);
    }
}
