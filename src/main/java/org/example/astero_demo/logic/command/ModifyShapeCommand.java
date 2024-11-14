package org.example.astero_demo.logic.command;

import org.example.astero_demo.adapter.model.ParamInfo;
import org.example.astero_demo.adapter.model.ShapeParam;
import org.example.astero_demo.controller.ModelController;
import org.example.astero_demo.controller.ViewController;

public class ModifyShapeCommand extends Command {
    private final ViewController viewController;
    private final ModelController modelController;
    private final int modifyShapeId;
    private ParamInfo[] paramInfos;

    public ModifyShapeCommand(
            final ViewController viewController,
            final ModelController modelController,
            final int modifyShapeId,
            ParamInfo... infos) {
        this.viewController = viewController;
        this.modelController = modelController;
        this.modifyShapeId = modifyShapeId;
        this.paramInfos = infos;
    }

    @Override
    public void doCommand() {
        for (final ParamInfo info : paramInfos) {
            final String oldValue = modelController.getShapeParam(modifyShapeId, info.getParam());
            info.setOldValue(oldValue);

            modelController.modifyShapeParam(modifyShapeId, info.getParam(), info.getNewValue());
        }
        viewController.onModifyUpdate();
    }

    @Override
    public void undoCommand() {
        for (final ParamInfo info : paramInfos) {
            modelController.modifyShapeParam(modifyShapeId, info.getParam(), info.getOldValue());
        }
        viewController.onModifyUpdate();
    }
}
