package org.example.astero_demo.adapter.model.state;

import org.apache.commons.lang3.StringUtils;
import org.example.astero_demo.adapter.model.entity.Shape;
import org.example.astero_demo.adapter.model.entity.ShapeType;
import org.example.astero_demo.adapter.model.metadata.ParamInfo;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public interface ModelState {

    Shape getShape(int id);

    Stream<Shape> findShapes(Predicate<Shape>... predicates);

    void saveShape(Shape shape);

    void removeShape(int id);

    Stream<Shape> getShapes();
}
