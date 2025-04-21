package org.example.astero_demo.logic;

import org.example.astero_demo.model.metadata.ParamInfo;

public interface ShapeProcessor {

    void createShape(ParamInfo[] paramInfos);

    void modifyShape(int shapeId, ParamInfo... paramInfos);

    void removeShape(int id);

    void undoLastOperation(); // TODO: move to separate category?
}
