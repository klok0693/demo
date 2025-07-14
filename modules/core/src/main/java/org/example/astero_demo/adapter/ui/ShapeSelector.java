package org.example.astero_demo.adapter.ui;

/**
 * Functional interface for selecting a shape by its ID
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
@FunctionalInterface
public interface ShapeSelector {

    void selectShape(String id);
}
