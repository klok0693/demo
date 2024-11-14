package org.example.astero_demo.logic.command;

import org.example.astero_demo.adapter.model.ShapeParam;
import org.example.astero_demo.controller.ModelController;
import org.example.astero_demo.controller.ViewController;

public class ModifyShapeCommand extends Command {
    private final ViewController viewController;
    private final ModelController modelController;
    private final int modifyShapeId;
    private final ShapeParam param;
    private final String newValue;

    private String oldValue;

    public ModifyShapeCommand(
            final ViewController viewController,
            final ModelController modelController,
            final int modifyShapeId,
            final ShapeParam param,
            final String newValue) {
        this.viewController = viewController;
        this.modelController = modelController;
        this.modifyShapeId = modifyShapeId;
        this.param = param;
        this.newValue = newValue;
    }

    @Override
    public void doCommand() {
        this.oldValue = modelController.getShapeParam(modifyShapeId, param);
        modifyParam(newValue);
    }

    @Override
    public void undoCommand() {
        modifyParam(oldValue);
    }

    private void modifyParam(final String value) {
        modelController.modifyShapeParam(modifyShapeId, param, value);
        viewController.onModifyUpdate();
    }
}
