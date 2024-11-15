package org.example.astero_demo.logic.command;

import lombok.Setter;
import org.example.astero_demo.adapter.model.ModelAdapter;
import org.example.astero_demo.adapter.model.ParamInfo;
import org.example.astero_demo.adapter.model.ShapeParam;
import org.example.astero_demo.adapter.model.ShapeType;
import org.example.astero_demo.adapter.ui.RootAdapter;
import org.example.astero_demo.controller.ModelController;
import org.example.astero_demo.controller.ViewController;

import static org.example.astero_demo.adapter.model.ParamInfo.create;

public class CommandFactory {
    @Setter
    private ViewController viewController;
    @Setter
    private ModelController modelController;

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

    public Command createModifyShapeCommand(final int shapeId, final ParamInfo... infos) {
        return new ModifyShapeCommand(viewController, modelController, shapeId, infos);
    }

    public Command createRemoveShapeCommand(final int id) {
        return new RemoveShapeCommand(modelController, viewController, id);
    }
}
