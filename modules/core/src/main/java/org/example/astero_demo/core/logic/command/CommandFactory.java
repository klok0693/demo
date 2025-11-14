package org.example.astero_demo.core.logic.command;

import org.example.astero_demo.model.metadata.dto.ShapeParams;

/**
 * Factory that provides methods to create different types of commands
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public interface CommandFactory {

    Command createNewShapeCommand(ShapeParams shapeParams);

    Command createModifyShapeCommand(int shapeId, ShapeParams shapeParams);

    Command createRemoveShapeCommand(int id);
}
