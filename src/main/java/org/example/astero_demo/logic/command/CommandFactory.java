package org.example.astero_demo.logic.command;

import org.example.astero_demo.adapter.model.entity.ShapeType;
import org.example.astero_demo.adapter.model.metadata.ParamInfo;

/**
 * Factory that provides methods to create different types of commands
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public interface CommandFactory {

    Command createNewShapeCommand(String priority, String x, String y, String width, String height, String color, ShapeType type);

    Command createModifyShapeCommand(int shapeId, ParamInfo... infos);

    Command createRemoveShapeCommand(int id);
}
