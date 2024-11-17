package org.example.astero_demo.logic.command;

import org.example.astero_demo.adapter.model.ParamInfo;
import org.example.astero_demo.adapter.model.ShapeParam;
import org.example.astero_demo.adapter.model.ShapeType;

import static org.example.astero_demo.adapter.model.ParamInfo.create;

public interface CommandFactory {

    Command createNewShapeCommand(String priority, String x, String y, String width, String height, String color, ShapeType type);

    Command createModifyShapeCommand(int shapeId, ParamInfo... infos);

    Command createRemoveShapeCommand(int id);
}
