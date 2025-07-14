package org.example.astero_demo.core.port.ui.elements;

import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import org.apache.commons.lang3.StringUtils;
import org.example.astero_demo.core.model.entity.Shape;
import org.example.astero_demo.core.model.state.ModelState;
import org.example.astero_demo.core.adapter.ui.ShapeSelector;
import org.example.astero_demo.core.adapter.ui.state.UIState;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

/**
 * Represents a tree view for displaying and managing layers of shapes.
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public interface LayersTree {
    void update();

    void unSelectAll();
}
