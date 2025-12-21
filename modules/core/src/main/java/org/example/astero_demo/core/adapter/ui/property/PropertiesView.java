package org.example.astero_demo.core.adapter.ui.property;

import org.example.astero_demo.core.adapter.ui.state.mode.ModeSwitchableView;
import org.example.astero_demo.model.metadata.ShapeParam;

/**
 * Represents a view for a Properties Panel
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public interface PropertiesView extends ModeSwitchableView {

    void updateX(String text);

    void updateY(String text);

    void updateWidth(String text);

    void updateHeight(String text);

    void updateLayer(String text);

    void updateColor(String text);
}
