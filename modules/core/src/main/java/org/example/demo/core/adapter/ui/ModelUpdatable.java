package org.example.demo.core.adapter.ui;

/**
 * Interface for classes that can be updated when model changes
 *
 * @author Pilip Yurchanka
 * @since v1.2
 */
@FunctionalInterface
public interface ModelUpdatable {

    void onModelUpdate();
}
