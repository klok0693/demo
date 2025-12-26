package org.example.demo.core.logic.command;

import org.example.demo.model.metadata.dto.ShapeParams;

/**
 * Command, having collection of the shape's parameters
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public abstract class ParamCommand extends Command {
    protected final ShapeParams shapeParams;

    protected ParamCommand(final ShapeParams shapeParams) {
        this.shapeParams = shapeParams;
    }
}
