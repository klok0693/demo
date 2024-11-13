package org.example.astero_demo.logic.command;

import lombok.Setter;
import org.example.astero_demo.adapter.model.ModelAdapter;
import org.example.astero_demo.adapter.model.ShapeType;
import org.example.astero_demo.adapter.ui.RootAdapter;

public class CommandFactory {
    @Setter
    private RootAdapter rootAdapter;
    private final ModelAdapter modelAdapter;

    public CommandFactory(final ModelAdapter modelAdapter) {
        this.modelAdapter = modelAdapter;
    }

    public Command createNewShapeCommand(final int priority, final double x, final double y, final ShapeType type) {
        return new CreateNewShapeCommand(rootAdapter, modelAdapter, priority, x, y, type);
    }
}
