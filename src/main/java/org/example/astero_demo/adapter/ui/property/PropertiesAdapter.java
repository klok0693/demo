package org.example.astero_demo.adapter.ui.property;

import org.example.astero_demo.adapter.ui.UpdatableAdapter;

/**
 * Represents an interface for interact with Properties Panel
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public interface PropertiesAdapter extends UpdatableAdapter {

    void updateX(String x);

    void updateY(String y);

    void updateWidth(String width);

    void updateHeight(String height);

    void updateLayer(String layer);

    void updateColor(String color);
}
