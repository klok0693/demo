package org.example.astero_demo.core.controller.keyboard;

public interface KeyboardController {

    void removeShape(int id);

    void undoLastOperation();

    void cut(int obj);

    void copy(int obj);

    void paste();
}
