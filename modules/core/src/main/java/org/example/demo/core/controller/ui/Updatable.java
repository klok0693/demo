package org.example.demo.core.controller.ui;

/**
 * Interface for classes that can be updated
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public interface Updatable {

    void onCreateUpdate(int id);

    void onModifyUpdate(int id);

    void onRemoveUpdate();
}
