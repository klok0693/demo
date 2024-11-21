package org.example.astero_demo.controller.ui;

/**
 * Interface for classes that can be updated in a UI context
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public interface UIUpdatable {

    void onCreateUpdate(int id);

    void onModifyUpdate(int id);

    void onRemoveUpdate();
}
