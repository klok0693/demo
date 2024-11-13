package org.example.astero_demo.logic.command;

import lombok.Setter;
import org.example.astero_demo.adapter.model.ModelAdapter;
import org.example.astero_demo.adapter.model.ShapeType;
import org.example.astero_demo.adapter.ui.RootAdapter;
import org.example.astero_demo.controller.ModelController;
import org.example.astero_demo.controller.ViewController;

public class CommandFactory {
    @Setter
    private ViewController viewController;
    @Setter
    private ModelController modelController;

    public Command createNewShapeCommand(final int priority, final double x, final double y, final ShapeType type) {
        return new CreateNewShapeCommand(viewController, modelController, priority, x, y, type);
    }

    public Command createRemoveShapeCommand(final int id) {
        return new RemoveShapeCommand(modelController, viewController, id);
    }
}
