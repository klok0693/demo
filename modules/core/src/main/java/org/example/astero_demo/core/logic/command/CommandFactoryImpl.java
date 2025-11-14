package org.example.astero_demo.core.logic.command;

import org.example.astero_demo.core.controller.model.ModelController;
import org.example.astero_demo.core.controller.ui.UIController;
import org.example.astero_demo.model.metadata.dto.ShapeParams;

/**
 * A factory implementation that provides methods to create different types of commands
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public class CommandFactoryImpl implements CommandFactory {
    private final UIController viewController;
    private final ModelController modelController;

    public CommandFactoryImpl(final UIController viewController, final ModelController modelController) {
        this.viewController = viewController;
        this.modelController = modelController;
    }

    @Override
    public Command createNewShapeCommand(final ShapeParams shapeParams) {
        return new CreateNewShapeCommand(viewController, modelController, shapeParams);
    }

    @Override
    public Command createModifyShapeCommand(final int shapeId, final ShapeParams shapeParams) {
        return new ModifyShapeCommand(viewController, modelController, shapeId, shapeParams);
    }

    @Override
    public Command createRemoveShapeCommand(final int id) {
        return new RemoveShapeCommand(viewController, modelController, id);
    }
}
