package org.example.astero_demo.logic.command;

import org.example.astero_demo.adapter.model.ModelAdapter;
import org.example.astero_demo.adapter.model.ShapeType;
import org.example.astero_demo.adapter.ui.RootAdapter;

public class CreateNewShapeCommand extends Command {
    private final RootAdapter rootAdapter;
    private final ModelAdapter modelAdapter;

    private final int priority;
    private final double x, y;
    private final ShapeType type;

    private int createdShapeId;

    public CreateNewShapeCommand(
            final RootAdapter rootAdapter,
            final ModelAdapter modelAdapter,
            final int priority,
            final double x,
            final double y,
            final ShapeType type) {
        this.rootAdapter = rootAdapter;
        this.modelAdapter = modelAdapter;
        this.priority = priority;
        this.x = x;
        this.y = y;
        this.type = type;
    }

    @Override
    public void doCommand() {
        this.createdShapeId = modelAdapter.saveShape(priority, x, y, type);
        rootAdapter.update();
    }

    @Override
    public void undoCommand() {
        modelAdapter.removeShape(createdShapeId);
        rootAdapter.update();
    }
}
