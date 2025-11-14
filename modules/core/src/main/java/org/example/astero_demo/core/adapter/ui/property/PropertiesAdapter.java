package org.example.astero_demo.core.adapter.ui.property;

import org.example.astero_demo.core.adapter.ui.UpdatableAdapter;
import org.example.astero_demo.core.adapter.ui.state.mode.ModeSwitcher;
import org.example.astero_demo.model.metadata.ShapeParam;

/**
 * Represents an interface for interact with Properties Panel
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public interface PropertiesAdapter extends UpdatableAdapter, ModeSwitcher {

    void updateField(ShapeParam param, String value);
}
