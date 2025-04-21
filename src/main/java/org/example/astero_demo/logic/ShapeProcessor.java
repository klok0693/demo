package org.example.astero_demo.logic;

import org.example.astero_demo.model.metadata.dto.ShapeParams;

/**
 * Handle shape-manipulation operations
 *
 * @author Pilip Yurchanka
 * @since  v1.1
 */
public interface ShapeProcessor {

    void createShape(ShapeParams shapeParams);

    void modifyShape(int shapeId, ShapeParams shapeParams);

    void removeShape(int id);

    void undoLastOperation(); // TODO: move to separate category?
}
