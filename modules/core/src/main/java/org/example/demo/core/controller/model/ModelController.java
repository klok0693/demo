package org.example.demo.core.controller.model;

import org.example.demo.model.entity.Shape;
import org.example.demo.model.entity.ShapeType;
import org.example.demo.model.metadata.ShapeParam;

/**
 * Represents a controller for managing models.
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public interface ModelController {

    String getShapeParam(int id, ShapeParam param);

    void modifyShapeParam(int id, ShapeParam param, String newValue);

    int saveShape(
            String priority,
            String x,
            String y,
            String width,
            String height,
            String color,
            ShapeType type);

    int saveShape(
            Integer id,
            String priority,
            String x,
            String y,
            String width,
            String height,
            String color,
            ShapeType type);

    Shape removeShape(int id);
}
