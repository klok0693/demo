package org.example.astero_demo.logic.command;

import org.example.astero_demo.adapter.model.metadata.ParamInfo;
import org.example.astero_demo.adapter.model.metadata.ShapeParam;
import org.example.astero_demo.adapter.model.entity.ShapeType;
import org.example.astero_demo.controller.ModelController;
import org.example.astero_demo.controller.ViewController;

import static org.example.astero_demo.adapter.model.metadata.ParamInfo.create;

public class CommandFactoryImpl implements CommandFactory {
    protected ViewController viewController;
    protected ModelController modelController;

    public CommandFactoryImpl(final ViewController viewController, final ModelController modelController) {
        this.viewController = viewController;
        this.modelController = modelController;
    }

    @Override
    public Command createNewShapeCommand(
            final String priority,
            final String x,
            final String y,
            final String width,
            final String height,
            final String color,
            final ShapeType type) {

        return new CreateNewShapeCommand(viewController, modelController,
                create(ShapeParam.PRIORITY, priority),
                create(ShapeParam.X, x),
                create(ShapeParam.Y, y),
                create(ShapeParam.WIDTH, width),
                create(ShapeParam.HEIGHT, height),
                create(ShapeParam.COLOR, color),
                create(ShapeParam.TYPE, type.name()));
    }

    @Override
    public Command createModifyShapeCommand(final int shapeId, final ParamInfo... infos) {
        return new ModifyShapeCommand(viewController, modelController, shapeId, infos);
    }

    @Override
    public Command createRemoveShapeCommand(final int id) {
        return new RemoveShapeCommand(modelController, viewController, id);
    }
}
