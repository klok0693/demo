package org.example.astero_demo.logic.command;

import org.example.astero_demo.adapter.model.metadata.ParamInfo;
import org.example.astero_demo.adapter.model.entity.ShapeType;

public interface CommandFactory {

    Command createNewShapeCommand(String priority, String x, String y, String width, String height, String color, ShapeType type);

    Command createModifyShapeCommand(int shapeId, ParamInfo... infos);

    Command createRemoveShapeCommand(int id);
}
