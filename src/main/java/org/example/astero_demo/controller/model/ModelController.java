package org.example.astero_demo.controller.model;

import org.example.astero_demo.adapter.model.entity.Shape;
import org.example.astero_demo.adapter.model.entity.ShapeType;
import org.example.astero_demo.adapter.model.metadata.ShapeParam;

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
