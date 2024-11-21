package org.example.astero_demo.adapter.keyboard;

/**
 * Handle editor's operations. Like copy, paste etc.
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public interface OperationAdapter {

    void handleDelete();

    void handleUndo();

    void handleCopy();

    void handlePaste();
}
