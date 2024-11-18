package org.example.astero_demo.logic.command;

import org.example.astero_demo.adapter.model.metadata.ParamInfo;
import org.example.astero_demo.adapter.model.metadata.ShapeParam;
import org.example.astero_demo.adapter.model.entity.ShapeType;
import org.example.astero_demo.controller.ModelController;
import org.example.astero_demo.controller.ViewController;

import static org.example.astero_demo.util.ParamUtils.getParamInfo;

public class CreateNewShapeCommand extends ParamCommand {
    private final ViewController viewController;
    private final ModelController modelController;

/*    private final String priority;
    private final String x, y, width, height;
    private final ShapeType type;*/

    private int createdShapeId;

    public CreateNewShapeCommand(
            final ViewController viewController,
            final ModelController modelController,
            final ParamInfo... infos
 /*           final String priority,
            final String x,
            final String y,
            final String width,
            final String height,
            final ShapeType type*/) {

        super(infos);
        this.viewController = viewController;
        this.modelController = modelController;
/*        this.priority = priority;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.type = type;*/
    }

    @Override
    public void doCommand() {
        this.createdShapeId = modelController.saveShape(
                getParamInfo(paramInfos, ShapeParam.PRIORITY),
                getParamInfo(paramInfos, ShapeParam.X),
                getParamInfo(paramInfos, ShapeParam.Y),
                getParamInfo(paramInfos, ShapeParam.WIDTH),
                getParamInfo(paramInfos, ShapeParam.HEIGHT),
                getParamInfo(paramInfos, ShapeParam.COLOR),
                ShapeType.valueOf(getParamInfo(paramInfos, ShapeParam.TYPE)));

        viewController.onCreateUpdate(getParamInfo(paramInfos, ShapeParam.X), getParamInfo(paramInfos, ShapeParam.Y));
    }

    @Override
    public void undoCommand() {
        modelController.removeShape(createdShapeId);
        viewController.onRemoveUpdate();
    }
}
